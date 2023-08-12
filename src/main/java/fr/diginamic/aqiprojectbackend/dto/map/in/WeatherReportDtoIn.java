package fr.diginamic.aqiprojectbackend.dto.map.in;

/**
 * Weather report DTO input
 * @param weatherCode Weather code
 * @param temperature Temperature
 * @param humidity Humidity
 * @param pressure Pressure
 * @param weatherStationId Weather station identifier
 * @param reportDateId Report date identifier
 */
public record WeatherReportDtoIn(int weatherCode,
                                 double temperature,
                                 double humidity,
                                 double pressure,
                                 int weatherStationId,
                                 int reportDateId) {
}
