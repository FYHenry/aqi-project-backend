package fr.diginamic.aqiprojectbackend.dto.account.out;

import java.time.LocalDateTime;

/** User Status DTO output */
public record UserStatusDtoOut(int id,
                               String label,
                               String explanation,
                               String memo,
                               LocalDateTime beginDate,
                               LocalDateTime endDate) {
}
