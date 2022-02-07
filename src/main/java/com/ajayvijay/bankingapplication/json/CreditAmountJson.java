package com.ajayvijay.bankingapplication.json;

public class CreditAmountJson {
    private Long userAccount;
    private Long transactionId;
    private Double creditAmount;
    private String transactionType;
    private Double accountBalance;
    private String message;
    private Long debitedFrom;
    private String createdBy;

    public CreditAmountJson() {
    }

    public CreditAmountJson(Long userAccount, Long transactionId, Double creditAmount, String transactionType, String message, Long debitedFrom, String createdBy) {
        this.userAccount = userAccount;
        this.transactionId = transactionId;
        this.creditAmount = creditAmount;
        this.transactionType = transactionType;
        this.message = message;
        this.debitedFrom = debitedFrom;
        this.createdBy = createdBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Long getUserAccount() {
        return userAccount;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public Long getDebitedFrom() {
        return debitedFrom;
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
