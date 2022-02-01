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
    @ManyToOne(cascade = CascadeType.ALL)
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

    @ManyToOne
    @JoinColumn(
            foreignKey = @ForeignKey(name = "username_of_account_holder_debit"),
            name = "useraccount_id",
            referencedColumnName = "accountNumber"
    )
    private UserAccount useraccount;

    public UserAccount getUseraccount() {
        return useraccount;
    }

    public void setUseraccount(UserAccount useraccount) {
        this.useraccount = useraccount;
    }

    public DebitAmount() {
    }

    public DebitAmount(UserAccount userAccount, Long transactionId, Double creditAmount, Double accountBalance, String message, String createdBy) {
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
