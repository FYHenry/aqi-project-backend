package fr.diginamic.aqiprojectbackend.dto.account.out;

/** Address DTO output */
public record AddressDtoOut(int id,
                            String addressLine1,
                            String addressLine2,
                            int cityId) {}
