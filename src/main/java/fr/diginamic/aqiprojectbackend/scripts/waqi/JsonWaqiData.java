package fr.diginamic.aqiprojectbackend.scripts.waqi;

public class JsonWaqiData {
    Integer aqi;
    Integer idx;
    JsonWaqiAttribution[] attributions;
    JsonWaqiCity city;
    String dominentpol;
    JsonWaqiIaqi iaqi;
    JsonWaqiTime time;
    JsonWaqiForecast forecast;
    JsonWaqiDebug debug;

    public JsonWaqiData() {
    }

    public Integer getAqi() {
        return aqi;
    }

    public void setAqi(Integer aqi) {
        this.aqi = aqi;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public JsonWaqiAttribution[] getAttributions() {
        return attributions;
    }

    public void setAttributions(JsonWaqiAttribution[] attributions) {
        this.attributions = attributions;
    }

    public JsonWaqiCity getCity() {
        return city;
    }

    public void setCity(JsonWaqiCity city) {
        this.city = city;
    }

    public String getDominentpol() {
        return dominentpol;
    }

    public void setDominentpol(String dominentpol) {
        this.dominentpol = dominentpol;
    }

    public JsonWaqiIaqi getIaqi() {
        return iaqi;
    }

    public void setIaqi(JsonWaqiIaqi iaqi) {
        this.iaqi = iaqi;
    }

    public JsonWaqiTime getTime() {
        return time;
    }

    public void setTime(JsonWaqiTime time) {
        this.time = time;
    }

    public JsonWaqiForecast getForecast() {
        return forecast;
    }

    public void setForecast(JsonWaqiForecast forecast) {
        this.forecast = forecast;
    }

    public JsonWaqiDebug getDebug() {
        return debug;
    }

    public void setDebug(JsonWaqiDebug debug) {
        this.debug = debug;
    }
}
