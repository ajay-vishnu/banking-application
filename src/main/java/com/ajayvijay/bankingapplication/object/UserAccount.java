package com.ajayvijay.bankingapplication.object;

import com.ajayvijay.bankingapplication.attribute.DefaultColumns;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "UserAccount")
@Table(
        name = "useraccount"
)
public class UserAccount extends DefaultColumns {
    @Column(
            name = "accountnumber",
            nullable = false
    )
    private Long accountNumber;
    @Column(
            name = "holdername",
            nullable = false
    )
    private String holderName;
    @Column(
            name = "phone",
            nullable = false
    )
    private String phone;
    @Column(
            name = "holderaddress",
            nullable = false
    )
    private String holderAddress;
    @Column(
            name = "governmentid",
            nullable = false
    )
    private String governmentId;
    @Column(
            name = "accountbalance",
            columnDefinition = "Decimal(10,2) default '0.0'"
    )
    private Double accountBalance;
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            foreignKey = @ForeignKey(name = "username_of_account_holder"),
            name = "username",
            referencedColumnName = "username"
    )
    private ClientUser clientUser;
    @JsonIgnore
    @OneToMany(
            mappedBy = "useraccount",
            fetch = FetchType.LAZY
    )
    private List<CreditAmount> creditAmounts = new ArrayList<>();

    public UserAccount() {
    }

    public UserAccount(Long accountNumber, String holderName, String phone, String holderAddress, String governmentId, Double accountBalance, String createdBy) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.phone = phone;
        this.holderAddress = holderAddress;
        this.governmentId = governmentId;
        this.accountBalance = accountBalance;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
        this.updatedBy = createdBy;
        this.updatedAt = LocalDateTime.now();
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getHolderAddress() {
        return holderAddress;
    }

    public void setHolderAddress(String holderAddress) {
        this.holderAddress = holderAddress;
    }

    public String getGovernmentId() {
        return governmentId;
    }

    public void setGovernmentId(String governmentId) {
        this.governmentId = governmentId;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedAt=" + updatedAt +
                ", isDeleted=" + isDeleted +
                ", accountNumber=" + accountNumber +
                ", holderName='" + holderName + '\'' +
                ", phone='" + phone + '\'' +
                ", holderAddress='" + holderAddress + '\'' +
                ", governmentId='" + governmentId + '\'' +
                ", accountBalance=" + accountBalance +
                ", clientUser=" + clientUser +
                ", creditAmounts=" + creditAmounts +
                '}';
    }
}
