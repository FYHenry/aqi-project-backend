package fr.diginamic.aqiprojectbackend.entity.forum;

import fr.diginamic.aqiprojectbackend.entity.account.UserAccount;
import jakarta.persistence.*;

/** Reaction */
@Entity
public class Reaction {

    /** Reaction type */
    public enum ReactionType {
        /** +1 */
        PLUS_ONE(1),
        /** -1 */
        MINUS_ONE(-1),
        /** 0 */
        ZERO(0);
        /** Value */
        private final int VALUE;

        /**
         * Constructor with parameters.
         * @param VALUE Value
         */
        ReactionType(int VALUE) {
            this.VALUE = VALUE;
        }
        /** Value getter */
        public int getValue() {
            return this.VALUE;
        }
    }
    /** Identifier */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /** Message */
    @ManyToOne
    private Message message;
    /** User account */
    @ManyToOne
    private UserAccount userAccount;
    /** Reaction type */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReactionType reactionType;

    /**
     * Default constructor
     */
    public Reaction() {}

    /**
     * Constructor with parameters.
     * @param message Message
     * @param userAccount User account
     * @param reactionType Reaction type
     */
    public Reaction(Message message,
                    UserAccount userAccount,
                    ReactionType reactionType) {
        this.message = message;
        this.userAccount = userAccount;
        this.reactionType = reactionType;
    }

    /** Identifier getter */
    public int getId() {
        return id;
    }
    /** Identifier setter */
    public void setId(int id) {
        this.id = id;
    }
    /** Message getter */
    public Message getMessage() {
        return message;
    }
    /** Message setter */
    public void setMessage(Message message) {
        this.message = message;
    }
    /** User account getter */
    public UserAccount getUserAccount() {
        return userAccount;
    }
    /** User account setter */
    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
    /** Reaction type getter */
    public ReactionType getReactionType() {
        return reactionType;
    }
    /** Reaction type setter */
    public void setReactionType(ReactionType reactionType) {
        this.reactionType = reactionType;
    }
}