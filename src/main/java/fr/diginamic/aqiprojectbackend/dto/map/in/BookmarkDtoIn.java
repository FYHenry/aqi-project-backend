package fr.diginamic.aqiprojectbackend.dto.map.in;

/**
 * Bookmark DTO input
 * @param forecastTypeId Forecast type identifier
 * @param userAccountId User account identifier
 */
public record BookmarkDtoIn(int forecastTypeId,
                            int userAccountId) {
}
