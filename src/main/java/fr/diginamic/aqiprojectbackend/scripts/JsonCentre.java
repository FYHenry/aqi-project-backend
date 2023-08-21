package fr.diginamic.aqiprojectbackend.scripts;

import java.util.List;

public class JsonCentre {
    String type;
    Double[] coordinates;

    public JsonCentre() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Double[] coordinates) {
        this.coordinates = coordinates;
    }
}
