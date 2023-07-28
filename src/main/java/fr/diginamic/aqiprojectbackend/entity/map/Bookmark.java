package fr.diginamic.aqiprojectbackend.entity.map;

import fr.diginamic.aqiprojectbackend.entity.account.UserAccount;
import jakarta.persistence.*;

@Entity
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private ForecastType forecastType;

    @ManyToOne
    private UserAccount userAccount;
    public Bookmark(){
    }

    public Bookmark(Integer id, ForecastType forecastType) {
        this.id = id;
        this.forecastType = forecastType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ForecastType getForecastType() {
        return forecastType;
    }

    public void setForecastType(ForecastType forecastType) {
        this.forecastType = forecastType;
    }
}
