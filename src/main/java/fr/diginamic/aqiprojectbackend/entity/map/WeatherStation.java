package fr.diginamic.aqiprojectbackend.entity.map;

import jakarta.persistence.Entity;

/** Weather station */
@Entity
public class WeatherStation extends Station {

    /**
     * Constructor with parameters.
     * @param latitude Latitude
     * @param longitude Longitude
     * @param city City
     */
    public WeatherStation(double latitude,
                          double longitude,
                          City city) {
        super(latitude, longitude, city);
    }
}
