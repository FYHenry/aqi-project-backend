package fr.diginamic.aqiprojectbackend.repository.map;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.diginamic.aqiprojectbackend.entity.map.Station;
/** Station repository */
public interface StationRepository extends JpaRepository<Station, Integer> {

}
