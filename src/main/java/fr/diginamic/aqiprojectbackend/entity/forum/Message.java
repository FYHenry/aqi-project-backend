package fr.diginamic.aqiprojectbackend.entity.forum;

import java.util.List;

import fr.diginamic.aqiprojectbackend.entity.account.UserAccount;
import jakarta.persistence.*;
import lombok.Data;
/** Message */
@Data
@Entity
public class Message {
    /** Identifier */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /** Text */
    private String text;
    /** Thread */
    @ManyToOne
    private Thread thread;
    /** User account */
    @ManyToOne
    private UserAccount userAccount;
    /** User account score */
    @OneToMany(mappedBy = "message")
    private List<Reaction> userAccountScore;

    /**
     * Constructor.
     */
    public Message() {
    }

    /**
     * Constructor with parameters.
     * @param text Test
     * @param thread Thread
     * @param userAccount User account
     * @param userAccountScore User account score
     */
    public Message(String text, Thread thread, UserAccount userAccount, List<Reaction> userAccountScore) {
        this.text = text;
        this.thread = thread;
        this.userAccount = userAccount;
        this.userAccountScore = userAccountScore;
    }

    /** Identifier getter */
    public int getId() {
        return id;
    }
}