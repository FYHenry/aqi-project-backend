package fr.diginamic.aqiprojectbackend.entity.map;

import jakarta.persistence.*;

@Entity
public class AirQualityReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer aqi;
    private Integer pm25;
    private Integer pm10;
    private Integer o3;
    private Integer no2;
    @ManyToOne
    private AirQualityStation airQualityStation;
    @ManyToOne
    private ReportDate reportDate;
    public AirQualityReport() {
    }

    public AirQualityReport(Integer id, Integer aqi, Integer pm25, Integer pm10, Integer o3, Integer no2, AirQualityStation airQualityStation, ReportDate reportDate) {
        this.id = id;
        this.aqi = aqi;
        this.pm25 = pm25;
        this.pm10 = pm10;
        this.o3 = o3;
        this.no2 = no2;
        this.airQualityStation = airQualityStation;
        this.reportDate = reportDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAqi() {
        return aqi;
    }

    public void setAqi(Integer aqi) {
        this.aqi = aqi;
    }

    public Integer getPm25() {
        return pm25;
    }

    public void setPm25(Integer pm25) {
        this.pm25 = pm25;
    }

    public Integer getPm10() {
        return pm10;
    }

    public void setPm10(Integer pm10) {
        this.pm10 = pm10;
    }

    public Integer getO3() {
        return o3;
    }

    public void setO3(Integer o3) {
        this.o3 = o3;
    }

    public Integer getNo2() {
        return no2;
    }

    public void setNo2(Integer no2) {
        this.no2 = no2;
    }

    public AirQualityStation getAirQualityStation() {
        return airQualityStation;
    }

    public void setAirQualityStation(AirQualityStation airQualityStation) {
        this.airQualityStation = airQualityStation;
    }

    public ReportDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(ReportDate reportDate) {
        this.reportDate = reportDate;
    }
}
