package fr.diginamic.aqiprojectbackend.entity.map;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double lattitude;
    private Double longitude;
    @ManyToOne
    private City city;

    public Station() {
    }

    public Station(Integer id, Double lattitude, Double longitude, City city) {
        this.id = id;
        this.lattitude = lattitude;
        this.longitude = longitude;
        this.city = city;
    }

    public Double getLattitude() {
        return lattitude;
    }

    public void setLattitude(Double lattitude) {
        this.lattitude = lattitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

