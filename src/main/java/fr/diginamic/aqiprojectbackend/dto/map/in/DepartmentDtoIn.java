package fr.diginamic.aqiprojectbackend.dto.map.in;

/**
 * Department DTO input
 * @param code Departmental code
 * @param name Name
 * @param regionId Region identifier
 */
public record DepartmentDtoIn(String insee, String name, int regionId) {
}
