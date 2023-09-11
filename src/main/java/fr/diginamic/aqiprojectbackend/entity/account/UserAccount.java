package fr.diginamic.aqiprojectbackend.entity.account;

import fr.diginamic.aqiprojectbackend.entity.forum.Message;
import fr.diginamic.aqiprojectbackend.entity.forum.Reaction;
import fr.diginamic.aqiprojectbackend.entity.forum.Topic;
import fr.diginamic.aqiprojectbackend.entity.forum.Thread;
import fr.diginamic.aqiprojectbackend.entity.map.Bookmark;
import jakarta.persistence.*;

import java.util.List;
/*  Profil utilisateur
 *  Incarne l’utilisateur.
 */
/** User account */
@Entity
public class UserAccount {
    /** Identifier */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /* Prénom */
    /** First name */
    @Column(length = 50, nullable = false)
    private String firstName;
    /* Nom */
    /** Last name */
    @Column(length = 50, nullable = false)
    private String lastName;
    /* Courriel */
    /** E-mail */
    @Column(length = 50, unique = true, nullable = false)
    private String email;
    /* Mot de passe */
    /** Password */
    @Column(nullable = false)
    private String password;
    /* Statut
    *  Prédéfini par l’administrateur.
    *  Pour étiqueter les membres.
    */
    /** User status */
    @ManyToMany
    @JoinTable(name = "between_account_and_status",
            joinColumns = @JoinColumn(name = "user_account_id"),
            inverseJoinColumns = @JoinColumn(name = "user_status_id"))
    private List<UserStatus> userStatusList;
    /* Adresse
     * Adresse principale de l’utilisateur.
     * Prédéfinie et commune à quelques utilisateurs.
     */
    /** Address */
    @ManyToOne
    @JoinColumn(nullable = false)
    private Address address;
    /* Rôle
     * Octroie certains privilèges à un utilisateur.
     */
    /** Role */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    /*  Marque-page ou favori
     *  Enregistre des paramètres.
     */
    /** Bookmark */
    @OneToMany(mappedBy = "userAccount")
    private List<Bookmark> bookmarks;
    /*  Sujet ou rubrique
     *  Regroupe ses fils de discussion.
     *  Géré par un administrateur.
     */
    /** Topic */
    @OneToMany(mappedBy = "userAccount")
    private List<Topic> topics;
    /*  Fil de discussion
     *  Regroupe les messages.
     *  Géré par un membre.
     */
    /** Thread */
    @OneToMany(mappedBy = "userAccount")
    private List<Thread> threads;
    /* Message
     * Géré par un membre.
     */
    /** Message */
    @OneToMany(mappedBy = "userAccount")
    private List<Message> messages;
    /* Réaction
     * Notation cumulative du membre au message.
     */
    /** Reaction */
    @OneToMany(mappedBy = "userAccount")
    private List<Reaction> reactions;

    /**
     * Default constructor
     */
    public UserAccount(){
    }

    /**
     * Constructor with parameters.
     * @param firstName First name
     * @param lastName Last name
     * @param email E-Mail
     * @param password Password
     * @param userStatusList User status list
     * @param role Role
     * @param address Address
     * @param bookmarks Bookmarks
     * @param topics Topics
     * @param threads Threads
     * @param messages Messages
     * @param reactions Reactions
     */
    public UserAccount(String firstName,
                       String lastName,
                       String email,
                       String password,
                       List<UserStatus> userStatusList,
                       Role role,
                       Address address,
                       List<Bookmark> bookmarks,
                       List<Topic> topics,
                       List<Thread> threads,
                       List<Message> messages,
                       List<Reaction> reactions) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userStatusList = userStatusList;
        this.role = role;
        this.address = address;
        this.bookmarks = bookmarks;
        this.topics = topics;
        this.threads = threads;
        this.messages = messages;
        this.reactions = reactions;
    }
    /** Identifier getter */
    public Integer getId() {
        return id;
    }
    /** Identifier setter */
    public void setId(Integer id) {
        this.id = id;
    }
    /** First name getter */
    public String getFirstName() {
        return firstName;
    }
    /** First name setter */
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

    public List<UserStatus> getUserStatusList() {
        return userStatusList;
    }

    public void setUserStatusList(List<UserStatus> userStatusList) {
        this.userStatusList = userStatusList;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Bookmark> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(List<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
    }

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
