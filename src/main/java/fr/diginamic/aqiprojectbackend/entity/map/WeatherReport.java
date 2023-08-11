package fr.diginamic.aqiprojectbackend.entity.map;

import jakarta.persistence.*;
/** Weather report */
@Entity
public class WeatherReport {
    /** Identifier */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /** Weather code */
    private int weatherCode;
    /** Temperature */
    private double temperature;
    /** Humidity */
    private double humidity;
    /** Pressure */
    private double pressure;
    /** Weather station */
    @ManyToOne
    private WeatherStation weatherStation;
    /** Report date */
    @ManyToOne
    private ReportDate reportDate;

    /**
     * Constructor with parameters.
     * @param weatherCode Weather code
     * @param temperature Temperature
     * @param humidity Humidity
     * @param pressure Pressure
     * @param weatherStation Weather station
     * @param reportDate Report date
     */
    public WeatherReport(int weatherCode,
                         double temperature,
                         double humidity,
                         double pressure,
                         WeatherStation weatherStation,
                         ReportDate reportDate) {
        this.weatherCode = weatherCode;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.weatherStation = weatherStation;
        this.reportDate = reportDate;
    }
    /** Identifier getter */
    public int getId() {
        return id;
    }
    /** Identifier setter */
    public void setId(int id) {
        this.id = id;
    }
    /** Weather code getter */
    public int getWeatherCode() {
        return weatherCode;
    }
    /** Weather code setter */
    public void setWeatherCode(int weatherCode) {
        this.weatherCode = weatherCode;
    }
    /** Temperature getter */
    public double getTemperature() {
        return temperature;
    }
    /** Temperature setter */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
    /** Humidity getter */
    public double getHumidity() {
        return humidity;
    }
    /** Humidity setter */
    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
    /** Pressure getter */
    public double getPressure() {
        return pressure;
    }
    /** Pressure setter */
    public void setPressure(double pressure) {
        this.pressure = pressure;
    }
    /** Weather station getter */
    public WeatherStation getWeatherStation() {
        return weatherStation;
    }
    /** Weather station setter */
    public void setWeatherStation(WeatherStation weatherStation) {
        this.weatherStation = weatherStation;
    }
    /** Report date getter */
    public ReportDate getReportDate() {
        return reportDate;
    }
    /** Report date setter */
    public void setReportDate(ReportDate reportDate) {
        this.reportDate = reportDate;
    }
}
