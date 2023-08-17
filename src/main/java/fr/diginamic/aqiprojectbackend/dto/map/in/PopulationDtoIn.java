package fr.diginamic.aqiprojectbackend.dto.map.in;

import java.time.LocalDate;

/**
 * Population DTO input
 * @param date Date
 * @param label Label
 * @param populationNumber Population number
 * @param cityId City identifier
 */
public record PopulationDtoIn(LocalDate date,
                              String label,
                              long populationNumber,
                              int cityId) {}
