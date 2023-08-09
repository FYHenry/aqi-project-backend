package fr.diginamic.aqiprojectbackend.entity.forum;

import fr.diginamic.aqiprojectbackend.entity.account.UserAccount;
import jakarta.persistence.*;
/** Reaction */
@Entity
public class Reaction {
    /** Reaction type */
    private enum ReactionType {
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
    /** Identifier getter */
    public int getId() {
        return id;
    }
}