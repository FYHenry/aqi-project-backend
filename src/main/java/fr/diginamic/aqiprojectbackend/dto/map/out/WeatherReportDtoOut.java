package fr.diginamic.aqiprojectbackend.dto.map.out;

/** Weather report DTO output */
public record WeatherReportDtoOut(int id,
                                  int weatherCode,
                                  double temperature,
                                  double humidity,
                                  double pressure,
                                  int weatherStationId,
                                  int reportDateId) {
}
