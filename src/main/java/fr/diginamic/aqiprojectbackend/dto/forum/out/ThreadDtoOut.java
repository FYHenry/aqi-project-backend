package fr.diginamic.aqiprojectbackend.dto.forum.out;

import java.util.List;

/**
 * Thread DTO output
 * @param id Identifier
 * @param title Title
 * @param topicId Topic identifier
 * @param messageIds Messages identifiers
 * @param userAccountId User account identifier
 */
public record ThreadDtoOut(int id,
                           String title,
                           int topicId,
                           List<Integer> messageIds,
                           int userAccountId) {}
