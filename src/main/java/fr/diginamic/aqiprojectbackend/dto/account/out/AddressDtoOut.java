package fr.diginamic.aqiprojectbackend.dto.account.out;

/**
 * Address DTO output
 * @param id Identifier
 * @param addressLine1 Address line #1
 * @param addressLine2 Address line #2
 * @param cityId City identifier
 */
public record AddressDtoOut(int id,
                            String addressLine1,
                            String addressLine2,
                            String cityId) {}
