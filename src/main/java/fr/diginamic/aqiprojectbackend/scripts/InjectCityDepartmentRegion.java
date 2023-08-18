package fr.diginamic.aqiprojectbackend.scripts;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.diginamic.aqiprojectbackend.entity.map.Region;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class InjectCityDepartmentRegion {
  public static void main(String[] args) throws IOException {
    Region occitanie = new Region();
    occitanie.setInsee("76");
    occitanie.setName("Occitanie");

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

    System.out.println(response);

    ObjectMapper objectMapper = new ObjectMapper();
    List<JsonDepartment> departments = objectMapper.readValue(response.toString(), new TypeReference<List<JsonDepartment>>() {});

    for (JsonDepartment department : departments){
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

      System.out.println(response2);
    }
  }
}
