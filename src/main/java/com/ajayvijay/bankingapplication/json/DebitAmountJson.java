package com.ajayvijay.bankingapplication.json;

public class DebitAmountJson {
    private Long userAccount;
    private Long transactionId;
    private Double debitAmount;
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

    public Double getDebitAmount() {
        return debitAmount;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public String getMessage() {
        return message;
    }
}
