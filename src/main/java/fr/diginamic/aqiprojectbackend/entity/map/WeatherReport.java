package fr.diginamic.aqiprojectbackend.entity.map;

import jakarta.persistence.*;

@Entity
public class WeatherReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer weatherCode;
    private Double temperature;
    private Double humidity;
    private Double pressure;
    @ManyToOne
    private WeatherStation weatherStation;
    @ManyToOne
    private ReportDate reportDate;

    public WeatherReport() {
    }

    public WeatherReport(Integer id, Integer weatherCode, Double temperature, Double humidity, Double pressure, WeatherStation weatherStation, ReportDate reportDate) {
        this.id = id;
        this.weatherCode = weatherCode;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.weatherStation = weatherStation;
        this.reportDate = reportDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(Integer weatherCode) {
        this.weatherCode = weatherCode;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public WeatherStation getWeatherStation() {
        return weatherStation;
    }

    public void setWeatherStation(WeatherStation weatherStation) {
        this.weatherStation = weatherStation;
    }

    public ReportDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(ReportDate reportDate) {
        this.reportDate = reportDate;
    }
}
