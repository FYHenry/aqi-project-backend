package fr.diginamic.aqiprojectbackend.scripts.waqi;

public class JsonWaqi {
    String status;
    JsonWaqiData data;

    public JsonWaqi() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public JsonWaqiData getData() {
        return data;
    }

    public void setData(JsonWaqiData data) {
        this.data = data;
    }
}
