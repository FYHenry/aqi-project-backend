package fr.diginamic.aqiprojectbackend.entity.account;

import fr.diginamic.aqiprojectbackend.entity.map.City;
import jakarta.persistence.*;
/** Address */
@Entity
public class Address {
    /** Identifier */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /** Address line #1 */
    @Column(nullable = false)
    private String addressLine1;
    /** Address line #2 */
    private String addressLine2;
    /** City */
    @ManyToOne
    private City city;

    /**
     * Constructor.
     */
    public Address(){
    }

    /**
     * Constructor with parameters.
     * @param addressLine1 Address line #1
     * @param addressLine2 Address line #2
     * @param city City
     */
    public Address(String addressLine1, String addressLine2, City city) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
    }
    /** Identifier getter */
    public int getId() {
        return id;
    }
    /** Identifier setter */
    public void setId(Integer id) {
        this.id = id;
    }
    /** Address line #1 getter */
    public String getAddressLine1() {
        return addressLine1;
    }
    /** Address line #1 setter */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }
    /** Address line #2 getter */
    public String getAddressLine2() {
        return addressLine2;
    }
    /** Address line #2 setter */
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }
    /** City getter */
    public City getCity() {
        return city;
    }
    /** City setter */
    public void setCity(City city) {
        this.city = city;
    }
}
