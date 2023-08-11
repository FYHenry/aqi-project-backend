package fr.diginamic.aqiprojectbackend.entity.map;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
/** Report date */
@Entity
public class ReportDate {
    /** Identifier */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /** Date */
    private LocalDateTime date;

    /**
     * Default constructor.
     */
    public ReportDate() {}

    /**
     * Constructor with parameters.
     * @param id Identifier
     * @param date Date
     */
    public ReportDate(int id, LocalDateTime date) {
        this.id = id;
        this.date = date;
    }

    /** Identifier getter */
    public int getId() {
        return id;
    }
    /** Identifier setter */
    public void setId(int id) {
        this.id = id;
    }
    /** Date getter */
    public LocalDateTime getDate() {
        return date;
    }
    /** Date setter */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
