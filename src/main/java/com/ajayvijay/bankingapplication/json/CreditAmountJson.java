package com.ajayvijay.bankingapplication.json;

public class CreditAmountJson {
    private Long userAccount;
    private Long transactionId;
    private Double creditAmount;
    private Double accountBalance;
    private String message;
    private String createdBy;

    public String getCreatedBy() {
        return createdBy;
    }

    public Long getUserAccount() {
        return userAccount;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public Double getCreditAmount() {
        return creditAmount;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public String getMessage() {
        return message;
    }
}
