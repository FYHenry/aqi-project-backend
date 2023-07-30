package fr.diginamic.aqiprojectbackend.entity.forum;

import fr.diginamic.aqiprojectbackend.entity.account.UserAccount;
import jakarta.persistence.*;

@Entity
public class Reaction {
    private enum ReactionType {
        PLUS_ONE(1),
        MINUS_ONE(-1),
        ZERO(0);
        private final int VALUE;
        ReactionType(int VALUE) {
            this.VALUE = VALUE;
        }
        public int getValue() {
            return this.VALUE;
        }
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Message message;
    @ManyToOne
    private UserAccount userAccount;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReactionType reactionType;

    public int getId() {
        return id;
    }
}