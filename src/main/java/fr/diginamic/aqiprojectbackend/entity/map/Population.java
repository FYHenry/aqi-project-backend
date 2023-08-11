package fr.diginamic.aqiprojectbackend.entity.map;

import jakarta.persistence.*;

import java.time.LocalDate;
/** Population */
@Entity
public class Population {
    /** Identifier */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /** Date */
    private LocalDate date;
    /** Label */
    private String label;
    /** Population number */
    private long populationNumber;
    /** City */
    @ManyToOne
    private City city;

    /**
     * Default constructor.
     */
    public Population() {}

    /**
     * Constructor with parameter.
     * @param date Date
     * @param label Label
     * @param populationNumber Population number
     * @param city City
     */
    public Population(LocalDate date,
                      String label,
                      long populationNumber,
                      City city) {
        this.date = date;
        this.label = label;
        this.populationNumber = populationNumber;
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
    /** Date getter */
    public LocalDate getDate() {
        return date;
    }
    /** Date setter */
    public void setDate(LocalDate date) {
        this.date = date;
    }
    /** Label getter */
    public String getLabel() {
        return label;
    }
    /** Label setter */
    public void setLabel(String label) {
        this.label = label;
    }
    /** Population number getter */
    public long getPopulationNumber() {
        return populationNumber;
    }
    /** Population number setter */
    public void setPopulationNumber(long populationNumber) {
        this.populationNumber = populationNumber;
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
