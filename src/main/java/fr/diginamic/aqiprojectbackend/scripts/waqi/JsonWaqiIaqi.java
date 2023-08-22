package fr.diginamic.aqiprojectbackend.scripts.waqi;

import jakarta.annotation.Nullable;

public class JsonWaqiIaqi {
    @Nullable
    JsonWaqiVDouble dew;
    @Nullable
    JsonWaqiVDouble co;
    @Nullable
    JsonWaqiVInteger h;
    @Nullable
    JsonWaqiVDouble no2;
    @Nullable
    JsonWaqiVDouble o3;
    @Nullable
    JsonWaqiVInteger p;
    @Nullable
    JsonWaqiVInteger pm10;
    @Nullable
    JsonWaqiVInteger pm25;
    @Nullable
    JsonWaqiVDouble so2;
    @Nullable
    JsonWaqiVInteger t;
    @Nullable
    JsonWaqiVDouble w;
    @Nullable
    JsonWaqiVInteger wg;

    public JsonWaqiIaqi() {
    }

    @Nullable
    public JsonWaqiVDouble getDew() {
        return dew;
    }

    public void setDew(@Nullable JsonWaqiVDouble dew) {
        this.dew = dew;
    }

    public JsonWaqiVDouble getCo() {
        return co;
    }

    public void setCo(JsonWaqiVDouble co) {
        this.co = co;
    }

    public JsonWaqiVInteger getH() {
        return h;
    }

    public void setH(JsonWaqiVInteger h) {
        this.h = h;
    }

    public JsonWaqiVDouble getNo2() {
        return no2;
    }

    public void setNo2(JsonWaqiVDouble no2) {
        this.no2 = no2;
    }

    public JsonWaqiVDouble getO3() {
        return o3;
    }

    public void setO3(JsonWaqiVDouble o3) {
        this.o3 = o3;
    }

    public JsonWaqiVInteger getP() {
        return p;
    }

    public void setP(JsonWaqiVInteger p) {
        this.p = p;
    }

    public JsonWaqiVInteger getPm10() {
        return pm10;
    }

    public void setPm10(JsonWaqiVInteger pm10) {
        this.pm10 = pm10;
    }

    public JsonWaqiVInteger getPm25() {
        return pm25;
    }

    public void setPm25(JsonWaqiVInteger pm25) {
        this.pm25 = pm25;
    }

    public JsonWaqiVDouble getSo2() {
        return so2;
    }

    public void setSo2(JsonWaqiVDouble so2) {
        this.so2 = so2;
    }

    public JsonWaqiVInteger getT() {
        return t;
    }

    public void setT(JsonWaqiVInteger t) {
        this.t = t;
    }

    public JsonWaqiVDouble getW() {
        return w;
    }

    public void setW(JsonWaqiVDouble w) {
        this.w = w;
    }

    @Nullable
    public JsonWaqiVInteger getWg() {
        return wg;
    }

    public void setWg(@Nullable JsonWaqiVInteger wg) {
        this.wg = wg;
    }
}
