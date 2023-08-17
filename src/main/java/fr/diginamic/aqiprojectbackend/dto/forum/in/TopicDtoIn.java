package fr.diginamic.aqiprojectbackend.dto.forum.in;

import java.util.List;

/**
 * Topic DTO input
 * @param title Title
 * @param threadIds Threads identifiers
 * @param userAccountId User account identifier
 */
public record TopicDtoIn(String title,
                         List<Integer> threadIds,
                         int userAccountId) {}
