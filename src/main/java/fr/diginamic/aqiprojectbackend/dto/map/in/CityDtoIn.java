package fr.diginamic.aqiprojectbackend.dto.map.in;

import java.util.List;

/**
 * City DTO input
 * @param insee INSEE
 * @param name Name
 * @param postcodes Postcodes
 * @param latitude Latitude
 * @param longitude Longitude
 * @param departmentId Department identifier
 */
public record CityDtoIn(String insee,
                        String name,
                        List<Integer> postcodes,
                        double latitude,
                        double longitude,
                        int departmentId) {}
