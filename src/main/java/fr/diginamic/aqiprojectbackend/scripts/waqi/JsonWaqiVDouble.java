package fr.diginamic.aqiprojectbackend.scripts.waqi;

import java.util.Optional;

public class JsonWaqiVDouble {
    Optional<Double> V;

    public JsonWaqiVDouble() {
    }

    public Optional<Double> getV() {
        return V;
    }

    public void setV(Optional<Double> v) {
        V = v;
    }
}
