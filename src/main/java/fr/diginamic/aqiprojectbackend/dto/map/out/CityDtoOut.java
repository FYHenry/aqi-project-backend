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
public record CityDtoOut(String insee,
                         String name,
                         java.util.List<Integer> postcode,
                         double latitude,
                         double longitude,
                         String departmentId) {}
