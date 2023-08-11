package fr.diginamic.aqiprojectbackend.entity.map;

import jakarta.persistence.*;
/** Department */
@Entity
public class Department {
    /** Departmental code (identifier) */
    @Id
    private int code;
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
     * @param code Departmental code
     * @param name Name
     * @param region Region
     */
    public Department(int code,
                      String name,
                      Region region) {
        this.code = code;
        this.name = name;
        this.region = region;
    }
    /** Departmental code getter */
    public int getCode() {
        return code;
    }
    /** Departmental code setter */
    public void setCode(int code) {
        this.code = code;
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
