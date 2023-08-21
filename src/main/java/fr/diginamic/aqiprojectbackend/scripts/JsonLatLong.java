package fr.diginamic.aqiprojectbackend.scripts;

public class JsonLatLong {
   String code;
   String nom;
   JsonCentre centre;
   String _score;

    public JsonLatLong() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public JsonCentre getCentre() {
        return centre;
    }

    public void setCentre(JsonCentre centre) {
        this.centre = centre;
    }

    public String get_score() {
        return _score;
    }

    public void set_score(String _score) {
        this._score = _score;
    }
}
