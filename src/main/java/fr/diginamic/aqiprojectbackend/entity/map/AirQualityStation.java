package fr.diginamic.aqiprojectbackend.entity.map;

import jakarta.persistence.Entity;

/** Air quality station */
@Entity
public class AirQualityStation extends Station {

    /**
     * Default constructor.
     */
    public AirQualityStation() {}

    /**
     * Constructor with parameters.
     * @param latitude Latitude
     * @param longitude Longitude
     * @param city City
     */
    public AirQualityStation(double latitude,
                             double longitude,
                             City city) {
        super(latitude, longitude, city);
    }
}

