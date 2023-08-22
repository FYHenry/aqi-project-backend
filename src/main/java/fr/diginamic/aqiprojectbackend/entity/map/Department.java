package fr.diginamic.aqiprojectbackend.entity.map;

import jakarta.persistence.*;
/** Department */
@Entity
public class Department {
    /** Departmental code (identifier) */
    @Id
    private String insee;
    /** Name */
    private String name;
    /** Region */
    @ManyToOne
    private Region region;

    /**
     * Default constructor.
     */
    public Department() {}

    /**
     * Constructor with parameters.
     * @param insee Departmental code
     * @param name Name
     * @param region Region
     */
    public Department(String insee,
                      String name,
                      Region region) {
        this.insee = insee;
        this.name = name;
        this.region = region;
    }
    /** Departmental code getter */
    public String getInsee() {
        return insee;
    }
    /** Departmental code setter */
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
    /** Region getter */
    public Region getRegion() {
        return region;
    }
    /** Region setter */
    public void setRegion(Region region) {
        this.region = region;
    }
}
