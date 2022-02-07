package com.ajayvijay.bankingapplication.json;

public class UserAccountJson {
    private String username;
    private String password;
    private String email;
    private String holderName;
    private String holderAddress;
    private String governmentId;
    private Double accountBalance;
    private String createdBy;
    private String phone;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getCreatedBy() {
        return createdBy;
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
