package fr.diginamic.aqiprojectbackend.dto.map.out;

import java.time.LocalDateTime;

/**
 * Report date DTO output
 * @param id Identifier
 * @param date Date
 */
public record ReportDateDtoOut(int id, LocalDateTime date) {
}
