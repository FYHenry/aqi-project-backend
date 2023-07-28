package fr.diginamic.aqiprojectbackend.entity.map;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Population {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate date;
    private String label;
    private Integer populationNumber;
    @ManyToOne
    private City city;

    public Population() {
    }

    public Population(Integer id, LocalDate date, String label, Integer populationNumber, City city) {
        this.id = id;
        this.date = date;
        this.label = label;
        this.populationNumber = populationNumber;
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getPopulationNumber() {
        return populationNumber;
    }

    public void setPopulationNumber(Integer populationNumber) {
        this.populationNumber = populationNumber;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
