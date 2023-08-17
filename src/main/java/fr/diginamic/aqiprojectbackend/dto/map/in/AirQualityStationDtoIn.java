package fr.diginamic.aqiprojectbackend.dto.map.in;

/**
 * Air quality station DTO input
 * @param latitude Latitude
 * @param longitude Longitude
 * @param cityId City identifier
 */
public record AirQualityStationDtoIn(double latitude,
                                     double longitude,
                                     int cityId) {}
