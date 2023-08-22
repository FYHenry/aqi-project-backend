package fr.diginamic.aqiprojectbackend.scripts.waqi;

public class JsonWaqiTime {
    String s;
    String tz;
    Integer v;
    String iso;

    public JsonWaqiTime() {
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getTz() {
        return tz;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }
}
