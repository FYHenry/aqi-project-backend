package fr.diginamic.aqiprojectbackend.entity.account;

import fr.diginamic.aqiprojectbackend.entity.forum.Message;
import fr.diginamic.aqiprojectbackend.entity.forum.Reaction;
import fr.diginamic.aqiprojectbackend.entity.forum.Topic;
import fr.diginamic.aqiprojectbackend.entity.forum.Thread;
import fr.diginamic.aqiprojectbackend.entity.map.Bookmark;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50, nullable = false)
    private String firstName;
    @Column(length = 50, nullable = false)
    private String lastName;
    @Column(length = 50, unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @ManyToOne
    private UserStatus userStatus;

    //@Column(nullable = false)
    @ManyToOne
    private Address address;

/*
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Credential credential;
*/
    @OneToMany
    private List<Topic> topics;
    @OneToMany
    private List<Thread> threads;
    @OneToMany
    private List<Message> messages;
    @OneToMany
    private List<Reaction> reactions;

    public UserAccount(){
    }

    //public UserAccount(Integer id, String firstName, String lastName, String email, String password, UserStatus userStatus, Address address, List<Bookmark> bookmarks, Credential credential, List<Topic> topics, List<Thread> threads, List<Message> messages, List<Reaction> reactions) {
    public UserAccount(Integer id, String firstName, String lastName, String email, String password, UserStatus userStatus, Address address, List<Bookmark> bookmarks, List<Topic> topics, List<Thread> threads, List<Message> messages, List<Reaction> reactions) {
       this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userStatus = userStatus;
        this.address = address;
        //this.credential = credential;
        this.topics = topics;
        this.threads = threads;
        this.messages = messages;
        this.reactions = reactions;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

/*
    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }
*/
    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public List<Thread> getThreads() {
        return threads;
    }

    public void setThreads(List<Thread> threads) {
        this.threads = threads;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
    }
}
