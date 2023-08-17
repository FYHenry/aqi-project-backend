package fr.diginamic.aqiprojectbackend.repository.map;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.diginamic.aqiprojectbackend.entity.map.AirQualityReport;
/** Air quality report repository */
public interface AirQualityReportRepository extends JpaRepository<AirQualityReport, Integer> {

}
