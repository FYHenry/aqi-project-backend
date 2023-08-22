package fr.diginamic.aqiprojectbackend.scripts.waqi;

import jakarta.annotation.Nullable;

public class JsonWaqiAttribution {
    String url;
    String name;
    @Nullable
    String logo;

    public JsonWaqiAttribution() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
