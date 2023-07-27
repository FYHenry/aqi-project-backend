package fr.diginamic.aqiprojectbackend.repository.map;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.diginamic.aqiprojectbackend.entity.map.AirQualityReport;

public interface AirQualityReportRepository extends JpaRepository<AirQualityReport, Integer> {

}
