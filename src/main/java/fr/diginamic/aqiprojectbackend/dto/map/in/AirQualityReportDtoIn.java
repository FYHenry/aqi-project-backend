package fr.diginamic.aqiprojectbackend.dto.map.in;

/** Air quality report DTO input */
public record AirQualityReportDtoIn(int aqi,
                                    int pm25,
                                    int pm10,
                                    int o3,
                                    int no2,
                                    int airQualityStationId,
                                    int reportDateId) {
}
