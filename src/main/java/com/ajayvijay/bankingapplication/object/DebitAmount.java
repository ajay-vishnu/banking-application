package com.ajayvijay.bankingapplication.object;

import com.ajayvijay.bankingapplication.attribute.DefaultColumns;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "DebitAmount")
@Table(
        name = "debitamount",
        uniqueConstraints = @UniqueConstraint(name = "unique_debit_transaction_id", columnNames = "transaction_id")
)
public class DebitAmount extends DefaultColumns {
    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            foreignKey = @ForeignKey(name = "account_number_of_amount_debited"),
            name = "accountnumber",
            referencedColumnName = "accountnumber"
    )
    private UserAccount userAccount;
    @Column(
            name = "transaction_id",
            nullable = false,
            updatable = false
    )
    private String transactionId;
    @Column(
            name = "debitamount",
            nullable = false,
            updatable = false
    )
    private Double debitAmount;
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
            foreignKey = @ForeignKey(name = "username_of_account_holder_debit"),
            name = "received_user_account",
            referencedColumnName = "accountNumber"
    )
    private UserAccount receivedUserAccount;

    public UserAccount getReceivedUserAccount() {
        return receivedUserAccount;
    }

    public void setReceivedUserAccount(UserAccount useraccount) {
        this.receivedUserAccount = useraccount;
    }

    public DebitAmount() {
    }

    public DebitAmount(UserAccount userAccount, String transactionId, Double debitAmount, Double accountBalance, String message, UserAccount receivedUserAccount, String createdBy) {
        this.userAccount = userAccount;
        this.transactionId = transactionId;
        this.debitAmount = debitAmount;
        this.accountBalance = accountBalance;
        this.message = message;
        this.receivedUserAccount = receivedUserAccount;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = createdBy;
    }

    public DebitAmount(UserAccount userAccount, String transactionId, Double debitAmount, Double accountBalance, String message, String createdBy) {
        this.userAccount = userAccount;
        this.transactionId = transactionId;
        this.debitAmount = debitAmount;
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

    public Double getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(Double creditAmount) {
        this.debitAmount = creditAmount;
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
                ", creditAmount=" + debitAmount +
                ", accountBalance=" + accountBalance +
                ", message='" + message + '\'' +
                '}';
    }
}
