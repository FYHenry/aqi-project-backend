package fr.diginamic.aqiprojectbackend.entity.account;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class UserStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_status_id")
    private int id;
    @Column(length = 50, nullable = false)
    private String label;
    @Column(nullable = false)
    private String explanation;
    @Column(nullable = false)
    private String memo;
    @Column(nullable = false)
    private LocalDateTime beginDate;
    private LocalDateTime endDate;
    @ManyToMany(mappedBy = "userStatusList")
    private List<UserAccount> userAccounts;

    public UserStatus(){
    }

    public UserStatus(Integer id,
                      String label,
                      String explanation,
                      String memo,
                      LocalDateTime beginDate,
                      LocalDateTime endDate) {
        this.id = id;
        this.label = label;
        this.explanation = explanation;
        this.memo = memo;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public LocalDateTime getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDateTime beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
