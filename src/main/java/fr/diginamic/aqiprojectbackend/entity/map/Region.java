package fr.diginamic.aqiprojectbackend.entity.map;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
/** Region */
@Entity
public class Region {
    /** Identifier */
    @Id
    private String insee;
    /** Name */
    private String name;

    /**
     * Default constructor.
     */
    public Region() {}

    /**
     * Constructor with parameters.
     * @param insee Insee
     * @param name Name
     */
    public Region(String insee, String name) {
        this.insee = insee;
        this.name = name;
    }

    public Region(String name) {
        this.name = name;
    }
    /** Identifier getter */
    public String getInsee() {
        return insee;
    }
    /** Identifier setter */
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
}
