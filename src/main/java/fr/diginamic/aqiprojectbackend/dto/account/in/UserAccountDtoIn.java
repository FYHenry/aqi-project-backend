package fr.diginamic.aqiprojectbackend.dto.account.in;

import java.util.List;
/** User account DTO input */
public record UserAccountDtoIn(String firstName,
                               String lastName,
                               String email,
                               String password,
                               List<Integer> userStatusIdList,
                               int addressId,
                               List<Integer> bookmarkIdList,
                               String role,
                               List<Integer> topicIdList,
                               List<Integer> threadIdList,
                               List<Integer> messageIdList,
                               List<Integer> reactionIdList) {
}
