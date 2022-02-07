package com.ajayvijay.bankingapplication.json;

public class DebitAmountJson {
    private Long userAccount;
    private Long transactionId;
    private Double debitAmount;
    private Double accountBalance;
    private String transactionType;
    private String message;
    private Long creditTo;
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

    public Long getCreditTo() {
        return creditTo;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getMessage() {
        return message;
    }
}
