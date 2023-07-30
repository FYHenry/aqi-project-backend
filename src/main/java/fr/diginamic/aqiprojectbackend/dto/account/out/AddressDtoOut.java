package fr.diginamic.aqiprojectbackend.dto.account.out;

import fr.diginamic.aqiprojectbackend.dto.map.out.CityDtoOut;

/** Address DTO output */
public record AddressDtoOut(int Id,
                            String addressLine1,
                            String addressLine2,
                            CityDtoOut city) {}
