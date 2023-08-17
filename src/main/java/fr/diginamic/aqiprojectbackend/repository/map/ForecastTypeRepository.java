package fr.diginamic.aqiprojectbackend.repository.map;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.diginamic.aqiprojectbackend.entity.map.ForecastType;
/** Forecast type repository */
public interface ForecastTypeRepository extends JpaRepository<ForecastType, Integer> {

}
