package fr.diginamic.aqiprojectbackend.dto.map.in;

/** Weather report DTO input */
public record WeatherReportDtoIn(int weatherCode,
                                 double temperature,
                                 double humidity,
                                 double pressure,
                                 int weatherStationId,
                                 int reportDateId) {
}
