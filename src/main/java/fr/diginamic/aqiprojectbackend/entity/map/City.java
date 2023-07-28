package fr.diginamic.aqiprojectbackend.entity.map;

import jakarta.persistence.*;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer insee;
    private String name;
    private Integer postcode;
    private Double latitude;
    private Double longitude;
    @ManyToOne
    private Departement departement;

    public City() {
    }

    public City(Integer insee, String name, Integer postcode, Double latitude, Double longitude, Departement departement) {
        this.insee = insee;
        this.name = name;
        this.postcode = postcode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.departement = departement;
    }

    public Integer getInsee() {
        return insee;
    }

    public void setInsee(Integer insee) {
        this.insee = insee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }
}
