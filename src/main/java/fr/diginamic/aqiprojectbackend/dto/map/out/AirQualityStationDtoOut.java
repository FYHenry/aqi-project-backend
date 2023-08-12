package fr.diginamic.aqiprojectbackend.dto.map.out;

/**
 * Air quality station DTO output
 * @param id Identifier
 * @param latitude Latitude
 * @param longitude Longitude
 * @param cityId City identifier
 */
public record AirQualityStationDtoOut(int id,
                                      double latitude,
                                      double longitude,
                                      int cityId) {}
