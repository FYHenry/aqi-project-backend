package fr.diginamic.aqiprojectbackend.dto.account.in;

import fr.diginamic.aqiprojectbackend.entity.map.City;

import java.util.List;

/**
 * User account DTO input
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

public record UserRegistrationFormDtoIn(
    String firstName,
    String lastName,
    String email,
    String password,
    String addressLine1,
    String addressLine2,
    String cityInsee){
}



