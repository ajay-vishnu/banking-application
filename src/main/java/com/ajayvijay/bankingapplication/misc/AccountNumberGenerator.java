package com.ajayvijay.bankingapplication.misc;

public class AccountNumberGenerator {
    public Long latestAccountNumber;
    private static AccountNumberGenerator accountNumberGenerator;
    private AccountNumberGenerator()    {

    }
    public static AccountNumberGenerator getAccountNumberGenerator()    {
        if (accountNumberGenerator == null) {
            accountNumberGenerator = new AccountNumberGenerator();
            accountNumberGenerator.latestAccountNumber = 1001001L;
        }
        else    {
            accountNumberGenerator.latestAccountNumber += 1;
        }
        return accountNumberGenerator;
    }
}
