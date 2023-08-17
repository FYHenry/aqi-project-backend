package fr.diginamic.aqiprojectbackend.dto.forum.in;

import java.util.List;

/**
 * Message DTO input
 * @param text Text
 * @param threadId Thread identifier
 * @param userAccountId User account identifier
 * @param reactionIds Reactions identifiers
 */
public record MessageDtoIn(String text,
                           int threadId,
                           int userAccountId,
                           List<Integer> reactionIds) {}
