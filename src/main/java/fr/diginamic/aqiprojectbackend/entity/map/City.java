package fr.diginamic.aqiprojectbackend.entity.map;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/** City */
@Entity
public class City {
    /** INSEE (identifier) */
    @Id
    private int insee;
    /** Name */
    private String name;
    /** Postcode */
    private int postcode;
    /** Latitude */
    private double latitude;
    /** Longitude */
    private double longitude;
    /** Department */
    @ManyToOne
    private Department department;

    /**
     * Constructor with parameters.
     * @param insee INSEE
     * @param name Name
     * @param postcode Postcode
     * @param latitude Latitude
     * @param longitude Longitude
     * @param department Department
     */
    public City(int insee,
                String name,
                int postcode,
                double latitude,
                double longitude,
                Department department) {
        this.insee = insee;
        this.name = name;
        this.postcode = postcode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.department = department;
    }
    /** INSEE getter */
    public int getInsee() {
        return insee;
    }
    /** INSEE setter */
    public void setInsee(int insee) {
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
    public int getPostcode() {
        return postcode;
    }
    /** Postcode setter */
    public void setPostcode(int postcode) {
        this.postcode = postcode;
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
