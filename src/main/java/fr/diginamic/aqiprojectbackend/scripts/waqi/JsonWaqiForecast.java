package fr.diginamic.aqiprojectbackend.scripts.waqi;

public class JsonWaqiForecast {
    JsonWaqiDaily daily;

    public JsonWaqiForecast() {
    }

    public JsonWaqiDaily getDaily() {
        return daily;
    }

    public void setDaily(JsonWaqiDaily daily) {
        this.daily = daily;
    }
}
