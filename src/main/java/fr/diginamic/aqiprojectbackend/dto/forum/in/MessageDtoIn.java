package fr.diginamic.aqiprojectbackend.dto.forum.in;

import java.util.List;

public record MessageDtoIn(String text,
                           int threadId,
                           int userAccountId,
                           List<Integer> reactionIds) {}
