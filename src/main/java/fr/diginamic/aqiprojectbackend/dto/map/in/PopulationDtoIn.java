package fr.diginamic.aqiprojectbackend.dto.map.in;

import java.time.LocalDate;
/** Population DTO input */
public record PopulationDtoIn(LocalDate date,
                              String label,
                              long populationNumber,
                              int cityId) {}
