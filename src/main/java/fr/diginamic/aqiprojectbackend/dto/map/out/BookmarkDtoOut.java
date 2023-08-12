package fr.diginamic.aqiprojectbackend.dto.map.out;

/**
 * Bookmark DTO output
 * @param id Identifier
 * @param forecastTypeId Forecast type identifier
 * @param userAccountId User account identifier
 */
public record BookmarkDtoOut(int id,
                             int forecastTypeId,
                             int userAccountId) {
}
