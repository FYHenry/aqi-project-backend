package fr.diginamic.aqiprojectbackend.dto;

import java.time.LocalDateTime;

/**
 * HTTP status DTO output
 * @param date Response Date
 * @param code Status code
 * @param message Status message
 * @param url Request URL
 */
public record HttpStatusDtoOut(LocalDateTime date,
                               int code,
                               String message,
                               String url) {}
