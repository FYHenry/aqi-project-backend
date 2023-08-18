package fr.diginamic.aqiprojectbackend.entity.map;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.List;

/** City */
@Entity
public class City {
    /** INSEE (identifier) */
    @Id
    private String insee;
    /** Name */
    private String name;
    /** Postcodes */
    @ElementCollection
    private List<Integer> postcodes;
    /** Latitude */
    private double latitude;
    /** Longitude */
    private double longitude;
    /** Department */
    @ManyToOne
    private Department department;

    /**
     * Default constructor.
     */
    public City() {
    }

    /**
     * Constructor with parameters.
     * @param insee INSEE
     * @param name Name
     * @param postcodes Postcode
     * @param latitude Latitude
     * @param longitude Longitude
     * @param department Department
     */
    public City(String insee,
                String name,
                List<Integer> postcodes,
                double latitude,
                double longitude,
                Department department) {
        this.insee = insee;
        this.name = name;
        this.postcodes = postcodes;
        this.latitude = latitude;
        this.longitude = longitude;
        this.department = department;
    }
    /** INSEE getter */
    public String getInsee() {
        return insee;
    }
    /** INSEE setter */
    public void setInsee(String insee) {
        this.insee = insee;
    }
    /** Name getter */
    public String getName() {
        return name;
    }
    /** Name setter */
    public void setName(String name) {
        this.name = name;
    }
    /** Postcode getter */
    public List<Integer> getPostcodes() {
        return postcodes;
    }
    /** Postcode setter */
    public void setPostcodes(List<Integer> postcodes) {
        this.postcodes = postcodes;
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
    /** Department getter */
    public Department getDepartement() {
        return department;
    }
    /** Department setter */
    public void setDepartement(Department department) {
        this.department = department;
    }
}
