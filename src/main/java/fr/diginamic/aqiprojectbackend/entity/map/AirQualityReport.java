package fr.diginamic.aqiprojectbackend.entity.map;

import jakarta.persistence.*;
/** Air quality Report */
@Entity
public class AirQualityReport {
    /** Identifier */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /** Air quality index */
    private int aqi;
    /** PM25 */
    private int pm25;
    /** PM10 */
    private int pm10;
    /** Ozone */
    private Double o3;
    /** Nitrogen dioxide */
    private Double no2;
    /** Air quality station */
    @ManyToOne
    private AirQualityStation airQualityStation;
    /** Report date */
    @ManyToOne
    private ReportDate reportDate;

    /**
     * Default constructor.
     */
    public AirQualityReport() {}

    /**
     * Constructor with parameters.
     * @param aqi Air quality index
     * @param pm25 PM25
     * @param pm10 PM10
     * @param o3 Ozone
     * @param no2 Nitrogen dioxide
     * @param airQualityStation Air quality station
     * @param reportDate Report date
     */
    public AirQualityReport(int aqi,
                            int pm25,
                            int pm10,
                            Double o3,
                            Double no2,
                            AirQualityStation airQualityStation,
                            ReportDate reportDate) {
        this.aqi = aqi;
        this.pm25 = pm25;
        this.pm10 = pm10;
        this.o3 = o3;
        this.no2 = no2;
        this.airQualityStation = airQualityStation;
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
    /** Air quality index getter */
    public int getAqi() {
        return aqi;
    }
    /** Air quality index setter */
    public void setAqi(int aqi) {
        this.aqi = aqi;
    }
    /** PM25 getter */
    public int getPm25() {
        return pm25;
    }
    /** PM10 setter */
    public void setPm25(int pm25) {
        this.pm25 = pm25;
    }
    /** PM10 getter */
    public int getPm10() {
        return pm10;
    }
    /** MP10 setter */
    public void setPm10(int pm10) {
        this.pm10 = pm10;
    }
    /** Ozone getter */
    public Double getO3() {
        return o3;
    }
    /** Ozone setter */
    public void setO3(Double o3) {
        this.o3 = o3;
    }
    /** Nitrogen dioxide getter */
    public Double getNo2() {
        return no2;
    }
    /** Nitrogen dioxide setter */
    public void setNo2(Double no2) {
        this.no2 = no2;
    }
    /** Air quality station getter */
    public AirQualityStation getAirQualityStation() {
        return airQualityStation;
    }
    /** Air quality station setter */
    public void setAirQualityStation(AirQualityStation airQualityStation) {
        this.airQualityStation = airQualityStation;
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
