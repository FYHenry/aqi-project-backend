package fr.diginamic.aqiprojectbackend.dto.map.out;

/**
 * Department DTO output
 * @param insee Departmental insee
 * @param name Name
 * @param regionId Region identifier
 */
public record DepartmentDtoOut(String insee, String name, String regionId) {
}
