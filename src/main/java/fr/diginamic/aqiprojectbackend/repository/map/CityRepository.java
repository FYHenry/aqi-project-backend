package fr.diginamic.aqiprojectbackend.repository.map;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import fr.diginamic.aqiprojectbackend.entity.map.City;
/** City repository */
public interface CityRepository extends JpaRepository<City, Integer> {
    Optional<City> findCityByInsee(String insee);


    Optional<List<City>>findByNameContaining(String name);
}

