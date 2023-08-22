package fr.diginamic.aqiprojectbackend.dto.map.out;

import java.time.LocalDate;

/**
 * Population DTO output
 * @param id Identifier
 * @param date Date
 * @param label Label
 * @param populationNumber Population number
 * @param cityId City identifier
 */
public record PopulationDtoOut(int id,
                               LocalDate date,
                               String label,
                               long populationNumber,
                               String cityId) {}
