package fr.diginamic.aqiprojectbackend.dto.map.out;

/**
 * Weather station DTO output
 * @param id Identifier
 * @param latitude Latitude
 * @param longitude Longitude
 * @param cityId City identifier
 */
public record WeatherStationDtoOut(int id,
                                   double latitude,
                                   double longitude,
                                   int cityId) {}
