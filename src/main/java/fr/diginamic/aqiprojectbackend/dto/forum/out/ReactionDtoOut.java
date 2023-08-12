package fr.diginamic.aqiprojectbackend.dto.forum.out;

/**
 * Reaction DTO output
 * @param id Identifier
 * @param messageId Message identifier
 * @param userAccount Use account identifier
 * @param reactionType Reaction type
 */
public record ReactionDtoOut(int id,
                             int messageId,
                             int userAccount,
                             String reactionType) {
}
