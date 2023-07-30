package fr.diginamic.aqiprojectbackend.dto.account.in;

/** Address DTO input */
public record AddressDtoIn(String addressLine1,
                           String addressLine2,
                           int cityId) {}
