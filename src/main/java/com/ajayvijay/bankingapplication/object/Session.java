package com.ajayvijay.bankingapplication.object;

import com.ajayvijay.bankingapplication.attribute.DefaultColumns;

import javax.persistence.*;

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
            name = "username",
            referencedColumnName = "username",
            foreignKey = @ForeignKey(name = "user_session_foreign_key")
    )
    ClientUser clientUser;

    public Session() {
    }

    public Session(ClientUser clientUser, String sessionId) {
        this.clientUser = clientUser;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public ClientUser getClientUser() {
        return clientUser;
    }

    public void setClientUser(ClientUser clientUser) {
        this.clientUser = clientUser;
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
                ", clientUser=" + clientUser +
                '}';
    }
}
