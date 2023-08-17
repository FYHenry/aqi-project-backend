package fr.diginamic.aqiprojectbackend.dto.account.in;

import java.time.LocalDateTime;
import java.util.List;

/**
 * User Status DTO input
 * @param label Label
 * @param explanation Explanation
 * @param memo Memo
 * @param beginDate Begin date
 * @param endDate End date
 * @param userAccountIds User account identifiers
 */
public record UserStatusDtoIn(String label,
                              String explanation,
                              String memo,
                              LocalDateTime beginDate,
                              LocalDateTime endDate,
                              List<Integer> userAccountIds) {
}
