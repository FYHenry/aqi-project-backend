package fr.diginamic.aqiprojectbackend.dto.map.out;


/**
 * Air quality report DTO output
 * @param id Identifier
 * @param aqi Air quality index
 * @param pm25 PM25
 * @param pm10 PM10
 * @param o3 Ozone
 * @param no2 Nitrogen dioxide
 * @param airQualityStationId Air quality station identifier
 * @param reportDateId Report date identifier
 */
public record AirQualityReportDtoOut(int id,
                                     int aqi,
                                     int pm25,
                                     int pm10,
                                     Double o3,
                                     Double no2,
                                     int airQualityStationId,
                                     int reportDateId) {
}
