package fr.diginamic.aqiprojectbackend.scripts;

import fr.diginamic.aqiprojectbackend.dto.map.in.CityDtoIn;
import fr.diginamic.aqiprojectbackend.scripts.waqi.JsonWaqi;
import fr.diginamic.aqiprojectbackend.util.JpaQueries;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class InjectReports {
    public static void main(String[] args) throws SQLException, IOException {

        final String waqiToken ="14d8363016e93ef126a59b2820832cc7c8b02fd1";
        final LocalDateTime time = LocalDateTime.now();
        final DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        final String formatDateTime = time.format(formatter);
        System.out.println(formatDateTime);
        final String query = String.format("INSERT INTO report_date (id, date) VALUES (1, '%s')",
                formatDateTime);
        JpaQueries.update(query);
        List<CityDtoIn> cities = 
                JpaQueries.extractEntities("http://127.0.0.1:8080/cities");
        int i = 1;
        for (CityDtoIn city : cities){
            final String waqiUrl = String.format("https://api.waqi.info/feed/geo:%f;%f/?token=%s",
                    city.latitude(),
                    city.longitude(),
                    waqiToken);
            final JsonWaqi waqi = JpaQueries.extractEntities(waqiUrl, JsonWaqi.class);

            double latitude = waqi.getData().getCity().getGeo()[0];
            double longitude = waqi.getData().getCity().getGeo()[1];
            int aqi = waqi.getData().getAqi();
            Optional<Double> no2 = waqi.getData().getIaqi().getNo2().getV();
            Optional<Double> o3 = waqi.getData().getIaqi().getO3().getV();
            int pm10 = waqi.getData().getIaqi().getPm10().getV();
            int pm25 = waqi.getData().getIaqi().getPm25().getV();

            final String airQS = String.format("INSERT INTO air_quality_station (id, latitude, longitude, city_insee) VALUES (%d, %f, %f, '%s')",
                    i,
                    latitude,
                    longitude,
                    city.insee());
            System.out.println(airQS);
            final String airQR = String.format("INSERT INTO air_quality_report (air_quality_station_id, aqi, no2, o3, pm10, pm25, report_date_id) VALUES (%d, %d, %f, %f, %d, %d, 1)",
                    i,
                    aqi,
                    no2.orElse(0.0),
                    o3.orElse(0.0),
                    pm10,
                    pm25);
            System.out.println(airQR);
            JpaQueries.update(airQS);
            JpaQueries.update(airQR);
            i++;
        }
    }
}
