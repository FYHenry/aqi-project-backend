package fr.diginamic.aqiprojectbackend.scripts;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.diginamic.aqiprojectbackend.dto.map.in.CityDtoIn;
import fr.diginamic.aqiprojectbackend.entity.map.City;
import fr.diginamic.aqiprojectbackend.repository.map.CityRepository;
import fr.diginamic.aqiprojectbackend.scripts.waqi.JsonWaqi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InjectReports {
    public static void main(String[] args) throws SQLException, IOException {

        String waqiToken ="14d8363016e93ef126a59b2820832cc7c8b02fd1";
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formatDateTime = time.format(formatter);
        System.out.println(formatDateTime);

        Connection conn= DriverManager.getConnection("jdbc:mariadb://localhost:3306/aqi_project", "dev", "dev");
        Statement statement = conn.createStatement();
        statement.executeUpdate("INSERT INTO report_date (id, date) VALUES (1, '"+formatDateTime+"')");
        conn.close();

        URL urlCity = new URL("http://127.0.0.1:8080/cities");
        HttpURLConnection con = (HttpURLConnection) urlCity.openConnection();
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
        List<CityDtoIn> cities = objectMapper.readValue(response.toString(), new TypeReference<List<CityDtoIn>>() {});

        Integer i = 1;
        for (CityDtoIn city : cities){

            URL urlAQI = new URL("https://api.waqi.info/feed/geo:"+city.latitude()+";"+city.longitude()+"/?token="+waqiToken);
            HttpURLConnection con1 = (HttpURLConnection) urlAQI.openConnection();
            con1.setRequestMethod("GET");

            BufferedReader in1 = new BufferedReader(
                    new InputStreamReader(con1.getInputStream()));
            String inputLine1;
            StringBuffer response1 = new StringBuffer();
            while ((inputLine1 = in1.readLine()) != null) {
                response1.append(inputLine1);
            }
            in1.close();
//            System.out.println(city.name()+", "+city.latitude()+";"+city.longitude());
//            System.out.println(response1);
            JsonWaqi waqi = objectMapper.readValue(response1.toString(), JsonWaqi.class);

            Double latitude = waqi.getData().getCity().getGeo()[0];
            Double longitude = waqi.getData().getCity().getGeo()[1];
            int aqi = waqi.getData().getAqi();
            Optional<Double> no2 = waqi.getData().getIaqi().getNo2().getV();
            Optional<Double> o3 = waqi.getData().getIaqi().getO3().getV();
            int pm10 = waqi.getData().getIaqi().getPm10().getV();
            int pm25 = waqi.getData().getIaqi().getPm25().getV();

            System.out.println("INSERT INTO air_quality_station (id, latitude, longitude, city_insee) VALUES ("+ i +", "+latitude+", "+longitude+", '"+city.insee()+"')");
            System.out.println("INSERT INTO air_quality_report (air_quality_station_id, aqi, no2, o3, pm10, pm25, report_date_id) VALUES ("+ i +", "+aqi+", "+no2+", "+o3+", "+pm10+", "+pm25+", 1)");

            Connection conn2= DriverManager.getConnection("jdbc:mariadb://localhost:3306/aqi_project", "dev", "dev");
            Statement statement2 = conn2.createStatement();
            statement2.executeUpdate("INSERT INTO air_quality_station (id, latitude, longitude, city_insee) VALUES ("+i+", "+latitude+", "+longitude+", '"+city.insee()+"')");
            conn2.close();

            Connection conn3= DriverManager.getConnection("jdbc:mariadb://localhost:3306/aqi_project", "dev", "dev");
            Statement statement3 = conn3.createStatement();
            statement3.executeUpdate("INSERT INTO air_quality_report (air_quality_station_id, aqi, no2, o3, pm10, pm25, report_date_id) VALUES ("+i+", "+aqi+", "+no2+", "+o3+", "+pm10+", "+pm25+", 1)");
            conn3.close();
            i++;
        }
    }
}
