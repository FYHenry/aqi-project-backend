package fr.diginamic.aqiprojectbackend.entity.map;

import jakarta.persistence.*;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int insee;
    private String name;
    private int postcode;
    private double latitude;
    private double longitude;
    @ManyToOne
    private Department department;

    public City() {
    }

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

    public int getInsee() {
        return insee;
    }

    public void setInsee(int insee) {
        this.insee = insee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Department getDepartement() {
        return department;
    }

    public void setDepartement(Department department) {
        this.department = department;
    }
}
