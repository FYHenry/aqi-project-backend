package fr.diginamic.aqiprojectbackend.entity.map;

import jakarta.persistence.*;

@Entity
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code;
    private String name;
    @ManyToOne
    private Region region;

    public Departement() {
    }

    public Departement(Integer code, String name, Region region) {
        this.code = code;
        this.name = name;
        this.region = region;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
