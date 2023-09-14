package fr.diginamic.aqiprojectbackend.dto.account.in;

public record UserUpdateProfileFormDtoIn(
            String firstName,
            String lastName,
            String email,
            String addressLine1,
            String addressLine2,
            String cityInsee){

}
