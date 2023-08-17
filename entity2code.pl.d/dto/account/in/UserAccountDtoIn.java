package fr.diginamic.aqiprojectbackend.dto.account.in;

import java.util.List;

/**
 * User account DTO input
 * @param firstName First name
 * @param lastName Last name
 * @param email Email
 * @param password Password
 * @param userStatusListIds User status list idenfifiers
 * @param addressId Address identifier
 * @param role Role
 * @param bookmarksIds Bookmarks idenfifiers
 * @param topicsIds Topics idenfifiers
 * @param threadsIds Threads idenfifiers
 * @param messagesIds Messages idenfifiers
 * @param reactionsIds Reactions idenfifiers
 */
public record UserAccountDtoIn(String firstName,
        String lastName,
        String email,
        String password,
        List<Integer> userStatusListIds,
        int addressId,
        String role,
        List<Integer> bookmarksIds,
        List<Integer> topicsIds,
        List<Integer> threadsIds,
        List<Integer> messagesIds,
        List<Integer> reactionsIds) {}
