package fr.diginamic.aqiprojectbackend.dto.map.out;

/**
 * Department DTO output
 * @param code Departmental code
 * @param name Name
 * @param regionId Region identifier
 */
public record DepartmentDtoOut(int code, String name, int regionId) {
}
