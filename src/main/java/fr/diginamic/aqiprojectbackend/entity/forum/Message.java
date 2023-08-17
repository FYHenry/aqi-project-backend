package fr.diginamic.aqiprojectbackend.entity.forum;

import java.util.List;

import fr.diginamic.aqiprojectbackend.entity.account.UserAccount;
import jakarta.persistence.*;

/** Message */
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
    private List<Reaction> reactions;

    /**
     * Default constructor.
     */
    public Message() {}

    /**
     * Constructor with parameters.
     * @param text Test
     * @param thread Thread
     * @param userAccount User account
     * @param reactions Reactions
     */
    public Message(String text,
                   Thread thread,
                   UserAccount userAccount,
                   List<Reaction> reactions) {
        this.text = text;
        this.thread = thread;
        this.userAccount = userAccount;
        this.reactions = reactions;
    }

    /** Identifier getter */
    public int getId() {
        return id;
    }
    /** Identifier setter */
    public void setId(int id) {
        this.id = id;
    }
    /** Text getter */
    public String getText() {
        return text;
    }
    /** Text setter */
    public void setText(String text) {
        this.text = text;
    }
    /** Thread getter */
    public Thread getThread() {
        return thread;
    }
    /** Thread setter */
    public void setThread(Thread thread) {
        this.thread = thread;
    }
    /** User account getter */
    public UserAccount getUserAccount() {
        return userAccount;
    }
    /** User account setter */
    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
    /** Reactions getter */
    public List<Reaction> getReactions() {
        return reactions;
    }
    /** Reactions setter */
    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
    }
}