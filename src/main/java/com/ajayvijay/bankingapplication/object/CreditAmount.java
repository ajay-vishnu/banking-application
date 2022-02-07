package com.ajayvijay.bankingapplication.object;

import com.ajayvijay.bankingapplication.attribute.DefaultColumns;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "CreditAmount")
@Table(
        name = "creditamount",
        uniqueConstraints = @UniqueConstraint(name = "unique_credit_transaction_id", columnNames = "transaction_id")
)
public class CreditAmount extends DefaultColumns {
    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            foreignKey = @ForeignKey(name = "account_number_of_amount_credited"),
            name = "accountnumber",
            referencedColumnName = "accountnumber"
    )
    private UserAccount userAccount;
    @Column(
            name = "transaction_id",
            nullable = false,
            updatable = false
    )
    private Long transactionId;
    @Column(
            name = "creditamount",
            nullable = false,
            updatable = false
    )
    private Double creditAmount;
    @Column(
            name = "accountbalance",
            nullable = false,
            updatable = false
    )
    private Double accountBalance;
    @Column(
            name = "message",
            nullable = false,
            updatable = false
    )
    private String message;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            foreignKey = @ForeignKey(name = "username_of_account_holder_credit"),
            name = "sent_user_account",
            referencedColumnName = "accountNumber"
    )
    private UserAccount sentUserAccount;

    public UserAccount getSentUserAccount() {
        return sentUserAccount;
    }

    public void setSentUserAccount(UserAccount useraccount) {
        this.sentUserAccount = useraccount;
    }

    public CreditAmount() {
    }

    public CreditAmount(UserAccount userAccount, Long transactionId, Double creditAmount, Double accountBalance, String message, UserAccount sentUserAccount, String createdBy) {
        this.userAccount = userAccount;
        this.transactionId = transactionId;
        this.creditAmount = creditAmount;
        this.accountBalance = accountBalance;
        this.message = message;
        this.sentUserAccount = sentUserAccount;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = createdBy;
    }

    public CreditAmount(UserAccount userAccount, Long transactionId, Double creditAmount, Double accountBalance, String message, String createdBy) {
        this.userAccount = userAccount;
        this.transactionId = transactionId;
        this.creditAmount = creditAmount;
        this.accountBalance = accountBalance;
        this.message = message;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = createdBy;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CreditAmount{" +
                "id=" + id +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedAt=" + updatedAt +
                ", isDeleted=" + isDeleted +
                ", userAccount=" + userAccount +
                ", creditAmount=" + creditAmount +
                ", accountBalance=" + accountBalance +
                ", message='" + message + '\'' +
                '}';
    }
}

