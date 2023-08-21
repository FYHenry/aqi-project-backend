package fr.diginamic.aqiprojectbackend.scripts;

import java.util.List;

public class JsonCity {
    String nom;
    String code;
    String codeDepartement;
    String siren;
    String codeEpci;
    String codeRegion;
    List<String> codesPostaux;
    String population;

    public JsonCity() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeDepartement() {
        return codeDepartement;
    }

    public void setCodeDepartement(String codeDepartement) {
        this.codeDepartement = codeDepartement;
    }

    public String getSiren() {
        return siren;
    }

    public void setSiren(String siren) {
        this.siren = siren;
    }

    public String getCodeEpci() {
        return codeEpci;
    }

    public void setCodeEpci(String codeEpci) {
        this.codeEpci = codeEpci;
    }

    public String getCodeRegion() {
        return codeRegion;
    }

    public void setCodeRegion(String codeRegion) {
        this.codeRegion = codeRegion;
    }

    public List<String> getCodesPostaux() {
        return codesPostaux;
    }

    public void setCodesPostaux(List<String> codesPostaux) {
        this.codesPostaux = codesPostaux;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }
}
