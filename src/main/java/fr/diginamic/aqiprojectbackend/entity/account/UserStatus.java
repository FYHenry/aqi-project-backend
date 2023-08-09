package fr.diginamic.aqiprojectbackend.entity.account;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
/** User status */
@Entity
public class UserStatus {
    /** Identifier */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_status_id")
    private int id;
    /** Label */
    @Column(length = 50, nullable = false)
    private String label;
    /** Explanation */
    @Column(nullable = false)
    private String explanation;
    /** Memo */
    @Column(nullable = false)
    private String memo;
    /** Begin date */
    @Column(nullable = false)
    private LocalDateTime beginDate;
    /** End date */
    private LocalDateTime endDate;
    /** User account */
    @ManyToMany(mappedBy = "userStatusList")
    private List<UserAccount> userAccounts;

    /**
     * Constructor.
     */
    public UserStatus(){
    }

    /**
     * Constructor with parameters.
     * @param id Identifier
     * @param label Label
     * @param explanation Explanation
     * @param memo Memo
     * @param beginDate Begin date
     * @param endDate End date
     */
    public UserStatus(int id,
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
    /** Identifier getter */
    public int getId() {
        return id;
    }
    /** Identifier setter */
    public void setId(int id) {
        this.id = id;
    }
    /** Label getter */
    public String getLabel() {
        return label;
    }
    /** Label setter */
    public void setLabel(String label) {
        this.label = label;
    }
    /** Explanation getter */
    public String getExplanation() {
        return explanation;
    }
    /** Explanation setter */
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
    /** Memo getter */
    public String getMemo() {
        return memo;
    }
    /** Memo setter */
    public void setMemo(String memo) {
        this.memo = memo;
    }
    /** Begin date getter */
    public LocalDateTime getBeginDate() {
        return beginDate;
    }
    /** Begin date setter */
    public void setBeginDate(LocalDateTime beginDate) {
        this.beginDate = beginDate;
    }
    /** End date getter */
    public LocalDateTime getEndDate() {
        return endDate;
    }
    /** End date setter */
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
