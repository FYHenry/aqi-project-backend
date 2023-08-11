package fr.diginamic.aqiprojectbackend.entity.forum;

import java.util.List;

import fr.diginamic.aqiprojectbackend.entity.account.UserAccount;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
/** Topic */
@Data
@Entity
public class Topic {
    /** Identifier */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /** Title */
    private String title;
    /** Threads */
    @OneToMany(mappedBy = "topic")
    private List<Thread> threads;
    /** User account */
    @ManyToOne
    private UserAccount userAccount;

    /**
     * Default constructor.
     */
    public Topic() {
    }

    /**
     * Constructor with parameters.
     * @param title Title
     * @param threads Threads
     * @param userAccount User account
     */
    public Topic(String title,
                 List<Thread> threads,
                 UserAccount userAccount) {
        this.title = title;
        this.threads = threads;
        this.userAccount = userAccount;
    }

    /** Identifier getter */
    public int getId() {
        return id;
    }
}