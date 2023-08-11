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

/** Thread */
@Data
@Entity
public class Thread {
    /** Identifier */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /** Title */
    private String title;
    /** Topic */
    @ManyToOne
    private Topic topic;
    /** Messages */
    @OneToMany(mappedBy = "thread")
    private List<Message> messages;
    /** User account */
    @ManyToOne
    private UserAccount userAccount;

    /**
     * Default constructor.
     */
    public Thread() {
    }

    /**
     * Constructor with parameters.
     * @param title Title
     * @param topic Topic
     * @param messages Message
     * @param userAccount User account
     */
    public Thread(String title,
                  Topic topic,
                  List<Message> messages,
                  UserAccount userAccount) {
        this.title = title;
        this.topic = topic;
        this.messages = messages;
        this.userAccount = userAccount;
    }
    /** Identifier */
    public int getId() {
        return id;
    }
}