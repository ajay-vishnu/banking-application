package com.ajayvijay.bankingapplication.object;

import com.ajayvijay.bankingapplication.attribute.DefaultColumns;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Session")
@Table(
        name = "session",
        uniqueConstraints = @UniqueConstraint(name = "unique_session_id", columnNames = "session_id")
)
public class Session extends DefaultColumns {
    @Column(
            name = "session_id",
            nullable = false,
            updatable = false
    )
    private String sessionId;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "accountNumber",
            referencedColumnName = "accountNumber",
            nullable = false,
            foreignKey = @ForeignKey(name = "user_session_foreign_key")
    )
    UserAccount userAccount;

    public Session() {
    }

    public Session(UserAccount userAccount, String sessionId) {
        this.sessionId = sessionId;
        this.userAccount = userAccount;
        this.createdBy = userAccount.getClientUser().getUsername();
        this.createdAt = LocalDateTime.now();
        this.updatedBy = userAccount.getClientUser().getUsername();
        this.updatedAt = LocalDateTime.now();
    }

    public String getSessionId() {
        return sessionId;
    }

    public UserAccount getClientUser() {
        return userAccount;
    }

    public void setClientUser(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedAt=" + updatedAt +
                ", isDeleted=" + isDeleted +
                ", sessionId='" + sessionId + '\'' +
                ", clientUser=" + userAccount +
                '}';
    }
}
