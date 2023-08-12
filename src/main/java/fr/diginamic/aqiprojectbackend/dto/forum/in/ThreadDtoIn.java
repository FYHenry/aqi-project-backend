package fr.diginamic.aqiprojectbackend.dto.forum.in;

import java.util.List;

/**
 * Thread DTO input
 * @param title Title
 * @param topicId Topic identifier
 * @param messageIds Messages identifiers
 * @param userAccountId User account identifier
 */
public record ThreadDtoIn(String title,
                          int topicId,
                          List<Integer> messageIds,
                          int userAccountId) {}
