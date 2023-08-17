package fr.diginamic.aqiprojectbackend.dto.map.out;

/**
 * Weather report DTO output
 * @param id Identifier
 * @param weatherCode Weather code
 * @param temperature Temperature
 * @param humidity Humidity
 * @param pressure Pressure
 * @param weatherStationId Weather station identifier
 * @param reportDateId Report date identifier
 */
public record WeatherReportDtoOut(int id,
                                  int weatherCode,
                                  double temperature,
                                  double humidity,
                                  double pressure,
                                  int weatherStationId,
                                  int reportDateId) {
}
