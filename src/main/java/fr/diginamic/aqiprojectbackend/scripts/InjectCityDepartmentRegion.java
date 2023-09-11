package fr.diginamic.aqiprojectbackend.scripts;

import fr.diginamic.aqiprojectbackend.entity.map.Region;
import fr.diginamic.aqiprojectbackend.scripts.deptGouv.JsonCity;
import fr.diginamic.aqiprojectbackend.scripts.deptGouv.JsonDepartment;
import fr.diginamic.aqiprojectbackend.scripts.deptGouv.JsonLatLong;
import fr.diginamic.aqiprojectbackend.util.JpaQueries;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

public class InjectCityDepartmentRegion {
  private static final String apiDomain = "https://geo.api.gouv.fr/";
  public static void main(String[] args) throws IOException, SQLException {
    final Region occitanie = new Region("76", "Occitanie");
    final LocalDate date = LocalDate.now();

    JpaQueries.update("INSERT INTO region VALUES ('76', 'Occitanie')");
    List<JsonDepartment> departments = JpaQueries.extractEntities(apiDomain+
            "regions/"
            +occitanie.getInsee()
            +"/departements");
    for (JsonDepartment department : departments){
      JpaQueries.update("INSERT INTO department VALUES ('"+
              department.getCode()+
              "', '"+
              department.getNom()+
              "', '76')");
      List<JsonCity> cities = JpaQueries.extractEntities(apiDomain+
              "departements/"+
              department.getCode()+
              "/communes");
      for (JsonCity city : cities){
        List<JsonLatLong> latLongs = JpaQueries.extractEntities(apiDomain+
                "communes?code="+
                city.getCode()+
                "&fields=code,nom,centre");
        for (JsonLatLong latLong : latLongs){
          double latitude = latLong.getCentre().getCoordinates()[1];
          double longitude = latLong.getCentre().getCoordinates()[0];
          String name = city.getNom();
          if (name.contains("'")){
            name = name.replace("'", "\\'");
          }

          System.out.printf("%s %s %s %f %f",
                  city.getCode(),
                  name,
                  department.getCode(),
                  latitude,
                  longitude);
          Connection connection= DriverManager.getConnection(JpaQueries.getUrl(),
                  JpaQueries.getUser(),
                  JpaQueries.getPassword());
          Statement statement = connection.createStatement();
          final String cityInsertion = String.format("INSERT INTO city (insee, name, department_insee, latitude, longitude) VALUES ('%s', '%s', '%s', %f, %f)",
                  city.getCode(),
                  name,
                  department.getCode(),
                  latitude,
                  longitude);
          statement.executeUpdate(cityInsertion);
          final String populationInsertion =
                  String.format("INSERT INTO population (population_number, city_insee, date) VALUES ('%s', '%s', '%tF')",
                          city.getPopulation(),
                          city.getCode(),
                          date);
          statement.executeUpdate(populationInsertion);

          for (String postcode : city.getCodesPostaux()) {
            statement.executeUpdate("INSERT INTO city_postcodes(postcodes, city_insee) VALUES ('"+postcode+"', '"+city.getCode()+"')");
          }
          connection.close();
        }
      }
    }
  }
}
