package com.ajayvijay.bankingapplication.service;

import com.ajayvijay.bankingapplication.json.CreditAmountJson;
import com.ajayvijay.bankingapplication.json.DebitAmountJson;
import com.ajayvijay.bankingapplication.object.DebitAmount;
import com.ajayvijay.bankingapplication.object.UserAccount;
import com.ajayvijay.bankingapplication.repository.DebitAmountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DebitAmountService {
    @Autowired
    private DebitAmountRepository debitAmountRepository;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private CreditAmountService creditAmountService;

    public List<DebitAmount> getAllTransactions(Long accountNumber) {
        UserAccount userAccount = userAccountService.getUserAccount(accountNumber).orElseThrow(() -> new IllegalStateException("User with account number " + accountNumber + " does not exist."));
        return debitAmountRepository.findAllByAccountNumber(userAccount);
    }

    public Optional<DebitAmount> getTransactionById(Long transactionId) {
        return debitAmountRepository.findByTransactionId(transactionId);
    }

    public void addNewTransaction(DebitAmountJson debitAmountJson)    {
        if (debitAmountJson.getCreatedBy() != null && debitAmountJson.getCreatedBy().length() > 0) {
            if (debitAmountJson.getTransactionType() != null && Objects.equals(debitAmountJson.getTransactionType(), "withdraw")) {
                UserAccount userAccount = userAccountService.getUserAccount(debitAmountJson.getUserAccount()).orElseThrow(() -> new IllegalStateException("User with account number " + debitAmountJson.getUserAccount() + " does not exist."));
                Double accountBalance = userAccount.getAccountBalance() - debitAmountJson.getDebitAmount();
                String transactionId = "D" + System.currentTimeMillis() + userAccount.getClientUser().getUsername();
                DebitAmount debitAmount = new DebitAmount(
                        userAccount,
                        transactionId,
                        debitAmountJson.getDebitAmount(),
                        accountBalance,
                        debitAmountJson.getMessage(),
                        userAccount.getClientUser().getUsername()
                );
                Optional<DebitAmount> debitAmountOptional = debitAmountRepository.findByTransactionId(debitAmountJson.getTransactionId());
                if (debitAmountOptional.isPresent()) {
                    throw new IllegalStateException("Transaction with ID " + debitAmountJson.getTransactionId() + " already completed.");
                }
                userAccount.setAccountBalance(accountBalance);
                userAccount.setUpdatedAt(LocalDateTime.now());
                userAccount.setUpdatedBy(userAccount.getClientUser().getUsername());
                debitAmountRepository.save(debitAmount);
            } else if (debitAmountJson.getTransactionType() != null && Objects.equals(debitAmountJson.getTransactionType(), "transfer"))  {
                UserAccount userAccount = userAccountService.getUserAccount(debitAmountJson.getUserAccount()).orElseThrow(() -> new IllegalStateException("User with account number " + debitAmountJson.getUserAccount() + " does not exist."));
                UserAccount receivedUserAccount = userAccountService.getUserAccount(debitAmountJson.getCreditTo()).orElseThrow(() -> new IllegalStateException("User with account number " + debitAmountJson.getCreditTo() + " does not exist."));
                Double accountBalance = userAccount.getAccountBalance() - debitAmountJson.getDebitAmount();
                String transactionId = "T" + System.currentTimeMillis() + userAccount.getClientUser().getUsername();
                DebitAmount debitAmount = new DebitAmount(
                        userAccount,
                        transactionId,
                        debitAmountJson.getDebitAmount(),
                        accountBalance,
                        debitAmountJson.getMessage(),
                        receivedUserAccount,
                        userAccount.getClientUser().getUsername()
                );
                Optional<DebitAmount> debitAmountOptional = debitAmountRepository.findByTransactionId(debitAmountJson.getTransactionId());
                if (debitAmountOptional.isPresent()) {
                    throw new IllegalStateException("Transaction with ID " + debitAmountJson.getTransactionId() + " already completed.");
                }
                userAccount.setAccountBalance(accountBalance);
                userAccount.setUpdatedAt(LocalDateTime.now());
                userAccount.setUpdatedBy(userAccount.getClientUser().getUsername());
                debitAmountRepository.save(debitAmount);
                CreditAmountJson creditAmountJson = new CreditAmountJson(
                        debitAmountJson.getCreditTo(),
                        transactionId,
                        debitAmountJson.getDebitAmount(),
                        "receive",
                        debitAmountJson.getMessage(),
                        debitAmountJson.getUserAccount(),
                        userAccount.getClientUser().getUsername()
                );
                creditAmountService.addNewTransaction(creditAmountJson);
            }
        }
        else    {
            throw new IllegalStateException("Must mention the createdBy parameter to update the database.");
        }
    }
}
