package fr.diginamic.aqiprojectbackend.scripts.waqi;

public class JsonWaqiDaily {
    Forecast[] o3;
    Forecast[] pm10;
    Forecast[] pm25;
    Forecast[] uvi;

    public JsonWaqiDaily() {
    }

    public Forecast[] getO3() {
        return o3;
    }

    public void setO3(Forecast[] o3) {
        this.o3 = o3;
    }

    public Forecast[] getPm10() {
        return pm10;
    }

    public void setPm10(Forecast[] pm10) {
        this.pm10 = pm10;
    }

    public Forecast[] getPm25() {
        return pm25;
    }

    public void setPm25(Forecast[] pm25) {
        this.pm25 = pm25;
    }

    public Forecast[] getUvi() {
        return uvi;
    }

    public void setUvi(Forecast[] uvi) {
        this.uvi = uvi;
    }
}
