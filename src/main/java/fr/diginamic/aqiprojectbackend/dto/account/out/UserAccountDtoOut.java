package fr.diginamic.aqiprojectbackend.dto.account.out;

import java.util.List;

/**
 * User account DTO input
 * @param id Identifier
 * @param firstName First name
 * @param lastName Last name
 * @param email E-mail
 * @param password Password
 * @param userStatusIds User status identifiers
 * @param addressId Address identifier
 * @param bookmarkIds Bookmark identifiers
 * @param role Role
 * @param topicIds Topic identifiers
 * @param threadIds Thread identifiers
 * @param messageIds Message identifiers
 * @param reactionIds Reaction identifiers
 */
public record UserAccountDtoOut(int id,
                               String firstName,
                               String lastName,
                               String email,
                               String password,
                               List<Integer> userStatusIds,
                               int addressId,
                               List<Integer> bookmarkIds,
                               String role,
                               List<Integer> topicIds,
                               List<Integer> threadIds,
                               List<Integer> messageIds,
                               List<Integer> reactionIds) {
}
