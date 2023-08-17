package fr.diginamic.aqiprojectbackend.dto.forum.out;

import java.util.List;

public record MessageDtoOut(int id,
                            String text,
                            int threadId,
                            int userAccountId,
                            List<Integer> reactionIds) {}
