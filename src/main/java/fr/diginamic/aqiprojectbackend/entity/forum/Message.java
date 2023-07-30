package fr.diginamic.aqiprojectbackend.entity.forum;

import java.util.List;

import fr.diginamic.aqiprojectbackend.entity.account.UserAccount;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String text;

    @ManyToOne
    private Thread thread;
    @ManyToOne
    private UserAccount userAccount;
    @OneToMany(mappedBy = "message")
    private List<Reaction> userAccountScore;

    public int getId() {
        return id;
    }
}