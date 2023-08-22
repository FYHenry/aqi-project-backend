package fr.diginamic.aqiprojectbackend.scripts.waqi;

public class JsonWaqiCity {
    Double[] geo;
    String name;
    String url;
    String location;

    public JsonWaqiCity() {
    }

    public Double[] getGeo() {
        return geo;
    }

    public void setGeo(Double[] geo) {
        this.geo = geo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
