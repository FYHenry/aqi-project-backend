package fr.diginamic.aqiprojectbackend.entity.map;

import jakarta.persistence.*;
/** Station */
@MappedSuperclass
public abstract class Station {
    /** Identifier */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /** Latitude */
    private double latitude;
    /** Longitude */
    private double longitude;
    /** City */
    @ManyToOne
    private City city;

    /**
     * Default constructor.
     */
    public Station() {}

    /**
     * Constructor with parameters.
     * @param latitude Latitude
     * @param longitude Longitude
     * @param city City
     */
    public Station(double latitude, double longitude, City city) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
    }
    /** Identifier getter */
    public int getId() {
        return id;
    }
    /** Identifier setter */
    public void setId(int id) {
        this.id = id;
    }
    /** Latitude getter */
    public double getLatitude() {
        return latitude;
    }
    /** Latitude setter */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    /** Longitude getter */
    public double getLongitude() {
        return longitude;
    }
    /** Longitude setter */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    /** City getter */
    public City getCity() {
        return city;
    }
    /** City setter */
    public void setCity(City city) {
        this.city = city;
    }
}

