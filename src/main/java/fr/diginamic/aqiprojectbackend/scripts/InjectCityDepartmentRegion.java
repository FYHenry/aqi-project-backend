package fr.diginamic.aqiprojectbackend.scripts;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.diginamic.aqiprojectbackend.entity.map.Region;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

public class InjectCityDepartmentRegion {
  public static void main(String[] args) throws IOException, SQLException {
    Region occitanie = new Region();
    occitanie.setInsee("76");
    occitanie.setName("Occitanie");
    LocalDate date = LocalDate.now();

    Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/aqi_project", "dev", "dev");
    Statement statement = conn.createStatement();
    statement.executeUpdate("INSERT INTO region VALUES ('76', 'Occitanie')");
    conn.close();

    URL urlDepartment = new URL("https://geo.api.gouv.fr/regions/"+occitanie.getInsee()+"/departements");
    HttpURLConnection con = (HttpURLConnection) urlDepartment.openConnection();
    con.setRequestMethod("GET");

    BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer response = new StringBuffer();
    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();


    ObjectMapper objectMapper = new ObjectMapper();
    List<JsonDepartment> departments = objectMapper.readValue(response.toString(), new TypeReference<List<JsonDepartment>>() {});

    for (JsonDepartment department : departments){
      Connection conn2= DriverManager.getConnection("jdbc:mariadb://localhost:3306/aqi_project", "dev", "dev");
      Statement statement2 = conn2.createStatement();
      statement2.executeUpdate("INSERT INTO department VALUES ('"+department.getCode()+"', '"+department.getNom()+"', '76')");
      conn2.close();

      URL urlCity = new URL("https://geo.api.gouv.fr/departements/"+department.getCode()+"/communes");
      HttpURLConnection con2 = (HttpURLConnection) urlCity.openConnection();
      con2.setRequestMethod("GET");

      BufferedReader in2 = new BufferedReader(
              new InputStreamReader(con2.getInputStream()));
      String inputLine2;
      StringBuffer response2 = new StringBuffer();
      while ((inputLine2 = in2.readLine()) != null) {
        response2.append(inputLine2);
      }
      in2.close();


      List<JsonCity> cities = objectMapper.readValue(response2.toString(), new TypeReference<List<JsonCity>>() {});

      for (JsonCity city : cities){
        URL urlLatLong = new URL("https://geo.api.gouv.fr/communes?code="+city.getCode()+"&fields=code,nom,centre");
        HttpURLConnection con3 = (HttpURLConnection) urlLatLong.openConnection();
        con3.setRequestMethod("GET");

        BufferedReader in3 = new BufferedReader(
                new InputStreamReader(con3.getInputStream()));
        String inputLine3;
        StringBuffer response3 = new StringBuffer();
        while ((inputLine3 = in3.readLine()) != null) {
          response3.append(inputLine3);
        }
        in3.close();

        List<JsonLatLong> latLongs = objectMapper.readValue(response3.toString(), new TypeReference<List<JsonLatLong>>() {});

        for (JsonLatLong latLong : latLongs){
          Double latitude = latLong.getCentre().coordinates[0];
          Double longitude = latLong.getCentre().coordinates[1];
          String name = city.getNom();
          if (name.contains("'")){
            name = name.replace("'", "\\'");
          }

          System.out.println(city.getCode()+" "+name+" "+department.getCode()+" "+latitude+" "+longitude);

          Connection conn3= DriverManager.getConnection("jdbc:mariadb://localhost:3306/aqi_project", "dev", "dev");
          Statement statement3 = conn3.createStatement();
          statement3.executeUpdate("INSERT INTO city (insee, name, department_insee, latitude, longitude) VALUES ('"+city.getCode()+"', '"+name+"', '"+department.getCode()+"', "+latitude+", "+longitude+")");
          statement3.executeUpdate("INSERT INTO population (population_number, city_insee, date) VALUES ('"+city.getPopulation()+"', '"+city.getCode()+"', '"+date+"')");

          for (String postcode : city.getCodesPostaux()) {
            statement3.executeUpdate("INSERT INTO city_postcodes(postcodes, city_insee) VALUES ('"+postcode+"', '"+city.getCode()+"')");
          }
          conn3.close();
        }
      }

    }
  }
}
