package com.ajayvijay.bankingapplication.service;

import com.ajayvijay.bankingapplication.json.CreditAmountJson;
import com.ajayvijay.bankingapplication.object.CreditAmount;
import com.ajayvijay.bankingapplication.object.UserAccount;
import com.ajayvijay.bankingapplication.repository.CreditAmountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CreditAmountService {
    @Autowired
    private CreditAmountRepository creditAmountRepository;

    @Autowired
    private UserAccountService userAccountService;

    public List<CreditAmount> getAllTransactions(Long accountNumber)    {
        UserAccount userAccount = userAccountService.getUserAccount(accountNumber).orElseThrow(() -> new IllegalStateException("User with account number " + accountNumber + " does not exist."));
        return creditAmountRepository.findAllByAccountNumber(userAccount);
    }

    public Optional<CreditAmount> getTransactionById(String transactionId)    {
        return creditAmountRepository.findByTransactionId(transactionId);
    }

    public void addNewTransaction(CreditAmountJson creditAmountJson)    {
        if (creditAmountJson.getCreatedBy() != null && creditAmountJson.getCreatedBy().length() > 0) {
            if (creditAmountJson.getTransactionType() != null && Objects.equals(creditAmountJson.getTransactionType(), "deposit"))  {
                UserAccount userAccount = userAccountService.getUserAccount(creditAmountJson.getUserAccount()).orElseThrow(() -> new IllegalStateException("User with account number " + creditAmountJson.getUserAccount() + " does not exist."));
                Double accountBalance = userAccount.getAccountBalance() + creditAmountJson.getCreditAmount();
                String transactionId = "C" + System.currentTimeMillis() + userAccount.getClientUser().getUsername();
                CreditAmount creditAmount = new CreditAmount(
                        userAccount,
                        transactionId,
                        creditAmountJson.getCreditAmount(),
                        accountBalance,
                        creditAmountJson.getMessage(),
                        userAccount.getClientUser().getUsername()
                );
                Optional<CreditAmount> creditAmountOptional = creditAmountRepository.findByTransactionId(creditAmountJson.getTransactionId());
                if (creditAmountOptional.isPresent()) {
                    throw new IllegalStateException("Transaction with ID " + creditAmountJson.getTransactionId() + " already completed.");
                }
                userAccount.setAccountBalance(accountBalance);
                userAccount.setUpdatedAt(LocalDateTime.now());
                userAccount.setUpdatedBy(userAccount.getClientUser().getUsername());
                userAccountService.updateUserAccountBalance(userAccount);
                creditAmountRepository.save(creditAmount);
            } else if (creditAmountJson.getTransactionType() != null && Objects.equals(creditAmountJson.getTransactionType(), "receive")) {
                UserAccount userAccount = userAccountService.getUserAccount(creditAmountJson.getUserAccount()).orElseThrow(() -> new IllegalStateException("User with account number " + creditAmountJson.getUserAccount() + " does not exist."));
                UserAccount senderUserAccount = userAccountService.getUserAccount(creditAmountJson.getDebitedFrom()).orElseThrow(() -> new IllegalStateException("User with account number " + creditAmountJson.getDebitedFrom() + " does not exist."));
                Double accountBalance = creditAmountJson.getCreditAmount() + userAccount.getAccountBalance();
                CreditAmount creditAmount = new CreditAmount(
                        userAccount,
                        creditAmountJson.getTransactionId(),
                        creditAmountJson.getCreditAmount(),
                        accountBalance,
                        creditAmountJson.getMessage(),
                        senderUserAccount,
                        userAccount.getClientUser().getUsername()
                );
                Optional<CreditAmount> creditAmountOptional = creditAmountRepository.findByTransactionId(creditAmountJson.getTransactionId());
                if (creditAmountOptional.isPresent()) {
                    throw new IllegalStateException("Transaction with ID " + creditAmountJson.getTransactionId() + " already completed.");
                }
                userAccount.setAccountBalance(accountBalance);
                userAccount.setUpdatedAt(LocalDateTime.now());
                userAccount.setUpdatedBy(userAccount.getClientUser().getUsername());
                userAccountService.updateUserAccountBalance(userAccount);
                creditAmountRepository.save(creditAmount);
            } else  {
                throw new IllegalStateException("Must provide a valid transaction type.");
            }
        }
        else    {
            throw new IllegalStateException("Must mention the createdBy parameter to update the database.");
        }
    }
}
