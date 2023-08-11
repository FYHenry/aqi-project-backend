package fr.diginamic.aqiprojectbackend.dto.map.out;

public record AirQualityStationDtoOut(int id,
                                      double latitude,
                                      double longitude,
                                      int cityId) {}
