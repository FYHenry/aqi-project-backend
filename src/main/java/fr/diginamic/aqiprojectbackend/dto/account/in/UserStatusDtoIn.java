package fr.diginamic.aqiprojectbackend.dto.account.in;

import java.time.LocalDateTime;

/** User Status DTO input */
public record UserStatusDtoIn(String label,
                              String explanation,
                              String memo,
                              LocalDateTime beginDate,
                              LocalDateTime endDate) {
}
