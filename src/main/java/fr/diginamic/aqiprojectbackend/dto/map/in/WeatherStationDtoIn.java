package fr.diginamic.aqiprojectbackend.dto.map.in;

/**
 * Weather station DTO input
 *
 * @param latitude Latitude
 * @param longitude Longitude
 * @param cityId City identifier
 */
public record WeatherStationDtoIn(double latitude,
                                  double longitude,
                                  int cityId) {}
