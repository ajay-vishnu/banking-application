package com.ajayvijay.bankingapplication.service;

import com.ajayvijay.bankingapplication.json.DebitAmountJson;
import com.ajayvijay.bankingapplication.object.DebitAmount;
import com.ajayvijay.bankingapplication.object.UserAccount;
import com.ajayvijay.bankingapplication.repository.DebitAmountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DebitAmountService {
    @Autowired
    private DebitAmountRepository debitAmountRepository;

    @Autowired
    private UserAccountService userAccountService;

    public List<DebitAmount> getAllTransactions(Long accountNumber) {
        UserAccount userAccount = userAccountService.getUserAccount(accountNumber).orElseThrow(() -> new IllegalStateException("User with account number " + accountNumber + " does not exist."));
        return debitAmountRepository.findAllByAccountNumber(userAccount);
    }

    public Optional<DebitAmount> getTransactionById(Long transactionId) {
        return debitAmountRepository.findByTransactionId(transactionId);
    }

    public void addNewTransaction(DebitAmountJson debitAmountJson)    {
        if (debitAmountJson.getCreatedBy() != null && debitAmountJson.getCreatedBy().length() > 0) {
            UserAccount userAccount = userAccountService.getUserAccount(debitAmountJson.getUserAccount()).orElseThrow(() -> new IllegalStateException("User with account number " + debitAmountJson.getUserAccount() + " does not exist."));
            DebitAmount debitAmount = new DebitAmount(
                    userAccount,
                    debitAmountJson.getTransactionId(),
                    debitAmountJson.getDebitAmount(),
                    debitAmountJson.getAccountBalance(),
                    debitAmountJson.getMessage(),
                    debitAmountJson.getCreatedBy()
            );
            Optional<DebitAmount> debitAmountOptional = debitAmountRepository.findByTransactionId(debitAmountJson.getTransactionId());
            if (debitAmountOptional.isPresent()) {
                throw new IllegalStateException("Transaction with ID " + debitAmountJson.getTransactionId() + " already completed.");
            }
            debitAmountRepository.save(debitAmount);
        }
        else    {
            throw new IllegalStateException("Must mention the createdBy parameter to update the database.");
        }
    }
}
