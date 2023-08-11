package fr.diginamic.aqiprojectbackend.entity.map;

import fr.diginamic.aqiprojectbackend.entity.account.UserAccount;
import jakarta.persistence.*;
/** Bookmark */
@Entity
public class Bookmark {
    /** Identifier */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /** Forecast type */
    @ManyToOne
    private ForecastType forecastType;
    /** User account */
    @ManyToOne
    private UserAccount userAccount;

    /**
     * Default constructor.
     */
    public Bookmark(){}

    /**
     * Constructor with parameters.
     * @param forecastType Forecast type
     * @param userAccount User account
     */
    public Bookmark(ForecastType forecastType,
                    UserAccount userAccount) {
        this.forecastType = forecastType;
        this.userAccount = userAccount;
    }
    /** Identifier getter */
    public int getId() {
        return id;
    }
    /** Identifier setter */
    public void setId(int id) {
        this.id = id;
    }
    /** Forecast type getter */
    public ForecastType getForecastType() {
        return forecastType;
    }
    /** Forecast type setter */
    public void setForecastType(ForecastType forecastType) {
        this.forecastType = forecastType;
    }
}
