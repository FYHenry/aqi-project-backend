package fr.diginamic.aqiprojectbackend.dto.map.in;

/**
 * Air quality report DTO input
 * @param aqi Air quality index
 * @param pm25 PM25
 * @param pm10 PM10
 * @param o3 Ozone
 * @param no2 Nitrogen dioxide
 * @param airQualityStationId Air quality station identifier
 * @param reportDateId Report date identifier
 */
public record AirQualityReportDtoIn(int aqi,
                                    int pm25,
                                    int pm10,
                                    int o3,
                                    int no2,
                                    int airQualityStationId,
                                    int reportDateId) {
}
