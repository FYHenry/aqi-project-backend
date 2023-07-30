package fr.diginamic.aqiprojectbackend.dto.account.in;

import java.time.LocalDateTime;

/** User Status Dto input */
public record UserStatusDtoIn(String label,
                              String explanation,
                              String memo,
                              LocalDateTime beginDate,
                              LocalDateTime endDate) {
}
