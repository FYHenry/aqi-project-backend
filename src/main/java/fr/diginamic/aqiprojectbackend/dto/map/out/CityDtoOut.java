package fr.diginamic.aqiprojectbackend.dto.map.out;

/**
 * City DTO output
 * @param insee INSEE
 * @param name Name
 * @param postcode Postcode
 * @param latitude Latitude
 * @param longitude Longitude
 * @param departmentId Department identifier
 */
public record CityDtoOut(int insee,
                         String name,
                         int postcode,
                         double latitude,
                         double longitude,
                         int departmentId) {}
