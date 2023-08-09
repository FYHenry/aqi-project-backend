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
    /** Identifier getter */
    public int getId() {
        return id;
    }
}