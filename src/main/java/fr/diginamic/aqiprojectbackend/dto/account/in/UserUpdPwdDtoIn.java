package fr.diginamic.aqiprojectbackend.dto.account.in;

public record UserUpdPwdDtoIn(
        String oldPassword,
        String newPassword){
}
