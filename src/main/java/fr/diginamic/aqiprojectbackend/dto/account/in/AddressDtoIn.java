package fr.diginamic.aqiprojectbackend.dto.account.in;

/**
 * Address DTO input
 * @param addressLine1 Address line #1
 * @param addressLine2 Address line #2
 * @param cityInsee City identifier
 */
public record AddressDtoIn(String addressLine1,
                           String addressLine2,
                           String cityInsee) {}
