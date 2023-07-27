package fr.diginamic.aqiprojectbackend.repository.map;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.diginamic.aqiprojectbackend.entity.map.WeatherStation;

public interface WeatherStationRepository extends JpaRepository<WeatherStation, Integer> {

}
