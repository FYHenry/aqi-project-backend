package fr.diginamic.aqiprojectbackend.dto.forum.out;

import java.util.List;

/**
 * Topic DTO output
 * @param id Identifier
 * @param title Title
 * @param threadIds Threads identifiers
 * @param userAccountId User account identifier
 */
public record TopicDtoOut(int id,
                          String title,
                          List<Integer> threadIds,
                          int userAccountId) {}
