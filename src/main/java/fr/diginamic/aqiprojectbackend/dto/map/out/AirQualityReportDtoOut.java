package fr.diginamic.aqiprojectbackend.dto.map.out;

/** Air quality report DTO output */
public record AirQualityReportDtoOut(int id,
                                     int aqi,
                                     int pm25,
                                     int pm10,
                                     int o3,
                                     int no2,
                                     int airQualityStationId,
                                     int reportDateId) {
}
