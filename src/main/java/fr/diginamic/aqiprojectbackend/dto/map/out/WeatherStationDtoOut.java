package fr.diginamic.aqiprojectbackend.dto.map.out;

public record WeatherStationDtoOut(int id,
                                   double latitude,
                                   double longitude,
                                   int cityId) {}
