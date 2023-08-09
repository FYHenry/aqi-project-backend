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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /** Name */
    private String name;

    /**
     * Constructor.
     */
    public Region() {
    }

    /**
     * Constructor with parameters.
     * @param id Identifier
     * @param name Name
     */
    public Region(int id, String name) {
        this.id = id;
        this.name = name;
    }
    /** Identifier getter */
    public int getId() {
        return id;
    }
    /** Identifier setter */
    public void setId(int id) {
        this.id = id;
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
