package fr.diginamic.aqiprojectbackend.entity.map;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
/** Forecast Type */
@Entity
public class ForecastType {
    /** Identifier */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /** Label */
    private String label;

    /**
     * Constructor.
     */
    public ForecastType() {
    }

    /**
     * Constructor with parameters.
     * @param label Label
     */
    public ForecastType(String label) {
        this.label = label;
    }
    /** Identifier getter */
    public int getId() {
        return id;
    }
    /** Identifier setter */
    public void setId(int id) {
        this.id = id;
    }
    /** Label getter */
    public String getLabel() {
        return label;
    }
    /** Label setter */
    public void setLabel(String label) {
        this.label = label;
    }
}
