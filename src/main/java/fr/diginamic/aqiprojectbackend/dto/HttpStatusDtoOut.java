package fr.diginamic.aqiprojectbackend.dto;

/**
 * HTTP status DTO output
 * @param code Status code
 * @param message Status message
 */
public record HttpStatusDtoOut(int code,
                               String message) {}
