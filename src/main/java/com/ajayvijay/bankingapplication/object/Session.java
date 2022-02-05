package com.ajayvijay.bankingapplication.object;

import com.ajayvijay.bankingapplication.attribute.DefaultColumns;

import javax.persistence.*;

@Entity(name = "Session")
@Table(
        name = "session",
        uniqueConstraints = @UniqueConstraint(name = "unique_private_key", columnNames = "privatekey")
)
public class Session extends DefaultColumns {
    @Column(
            name = "session_id",
            nullable = false,
            updatable = false
    )
    private String sessionId;
    @Column(
            name = "privatekey",
            nullable = false,
            updatable = false
    )
    private Long privateKey;
    @Column(
            name = "publickey",
            nullable = false,
            updatable = false
    )
    private Long publicKey;
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

    public Session(Long privateKey, Long publicKey, ClientUser clientUser) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.clientUser = clientUser;
    }

    public Long getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(Long privateKey) {
        this.privateKey = privateKey;
    }

    public Long getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(Long publickey) {
        this.publicKey = publickey;
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
                ", privateKey=" + privateKey +
                ", publickey=" + publicKey +
                ", clientUser=" + clientUser +
                '}';
    }
}
