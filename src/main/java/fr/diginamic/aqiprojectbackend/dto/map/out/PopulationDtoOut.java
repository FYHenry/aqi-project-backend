package fr.diginamic.aqiprojectbackend.dto.map.out;

import java.time.LocalDate;
/** Population DTO output */
public record PopulationDtoOut(int id,
                              LocalDate date,
                              String label,
                              long populationNumber,
                              int cityId) {}
