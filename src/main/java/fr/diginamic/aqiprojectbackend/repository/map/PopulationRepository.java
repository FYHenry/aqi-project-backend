package fr.diginamic.aqiprojectbackend.repository.map;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.diginamic.aqiprojectbackend.entity.map.Population;
/** Population repository */
public interface PopulationRepository extends JpaRepository<Population, Integer> {

}
