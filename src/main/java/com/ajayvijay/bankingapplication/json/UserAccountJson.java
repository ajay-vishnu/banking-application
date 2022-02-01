package com.ajayvijay.bankingapplication.json;

public class UserAccountJson {
    private Long accountNumber;
    private String holderName;
    private String holderAddress;
    private String governmentId;
    private Double accountBalance;
    private String createdBy;
    private String phone;

    public String getCreatedBy() {
        return createdBy;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public String getHolderAddress() {
        return holderAddress;
    }

    public String getGovernmentId() {
        return governmentId;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public String getPhone() {
        return phone;
    }
}
