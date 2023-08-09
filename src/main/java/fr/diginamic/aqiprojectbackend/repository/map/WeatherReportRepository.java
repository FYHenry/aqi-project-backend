package fr.diginamic.aqiprojectbackend.repository.map;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.diginamic.aqiprojectbackend.entity.map.WeatherReport;
/** Weather report repository */
public interface WeatherReportRepository extends JpaRepository<WeatherReport, Integer> {

}
