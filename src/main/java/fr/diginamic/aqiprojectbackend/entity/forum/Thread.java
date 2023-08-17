package fr.diginamic.aqiprojectbackend.entity.forum;

import java.util.List;

import fr.diginamic.aqiprojectbackend.entity.account.UserAccount;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

/** Thread */
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
    public Thread() {}

    /**
     * Constructor with parameters.
     * @param title Title
     * @param topic Topic
     * @param messages Messages
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
    /** Identifier getter */
    public int getId() {
        return id;
    }
    /** Identifier setter */
    public void setId(int id) {
        this.id = id;
    }
    /** Title getter */
    public String getTitle() {
        return title;
    }
    /** Title setter */
    public void setTitle(String title) {
        this.title = title;
    }
    /** Topic getter */
    public Topic getTopic() {
        return topic;
    }
    /** Topic setter */
    public void setTopic(Topic topic) {
        this.topic = topic;
    }
    /** Messages getter */
    public List<Message> getMessages() {
        return messages;
    }
    /** Messages setter */
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
    /** User account getter */
    public UserAccount getUserAccount() {
        return userAccount;
    }
    /** User account setter */
    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}