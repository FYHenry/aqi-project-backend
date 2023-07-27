package fr.diginamic.aqiprojectbackend.repository.map;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.diginamic.aqiprojectbackend.entity.map.AirQualityStation;

public interface AirQualityStationRepository extends JpaRepository<AirQualityStation, Integer> {

}
