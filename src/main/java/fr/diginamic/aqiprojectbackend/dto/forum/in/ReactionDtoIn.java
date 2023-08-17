package fr.diginamic.aqiprojectbackend.dto.forum.in;

/**
 * Reaction DTO input
 * @param messageId Message identifier
 * @param userAccount Use account identifier
 * @param reactionType Reaction type
 */
public record ReactionDtoIn(int messageId,
                            int userAccount,
                            String reactionType) {
}
