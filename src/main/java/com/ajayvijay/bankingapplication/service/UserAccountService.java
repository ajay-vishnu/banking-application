package com.ajayvijay.bankingapplication.service;

import com.ajayvijay.bankingapplication.json.UserAccountJson;
import com.ajayvijay.bankingapplication.object.UserAccount;
import com.ajayvijay.bankingapplication.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserAccountService {
    @Autowired
    private UserAccountRepository userAccountRepository;

    public Optional<UserAccount> getUserAccount(Long accountNumber)   {
        return userAccountRepository.findUserAccountByAccountNumber(accountNumber);
    }

    public void addNewAccountUser(UserAccountJson userAccountJson)  {
        if (userAccountJson.getCreatedBy() != null && userAccountJson.getCreatedBy().length() > 0) {
            UserAccount userAccount = new UserAccount(
                    userAccountJson.getAccountNumber(),
                    userAccountJson.getHolderName(),
                    userAccountJson.getPhone(),
                    userAccountJson.getHolderAddress(),
                    userAccountJson.getGovernmentId(),
                    userAccountJson.getAccountBalance(),
                    userAccountJson.getCreatedBy()
            );
            Optional<UserAccount> userAccountOptional = userAccountRepository.findUserAccountByAccountNumber(userAccountJson.getAccountNumber());
            if (userAccountOptional.isPresent()) {
                throw new IllegalStateException("User account with account number " + userAccountJson.getAccountNumber() + " already exists.");
            }
            userAccountRepository.save(userAccount);
        }
        else    {
            throw new IllegalStateException("Must mention the createdBy parameter to update the database.");
        }
    }

    public void deleteUserAccount(Long accountNumber, String deletedBy) {
        if (deletedBy != null && deletedBy.length() > 0)    {
            UserAccount userAccount = userAccountRepository.findUserAccountByAccountNumber(accountNumber).orElseThrow(() -> new IllegalStateException("User account with account number " + accountNumber + " does not exist."));
            userAccount.setUpdatedAt(LocalDateTime.now());
            userAccount.setUpdatedBy(deletedBy);
            userAccount.setDeleted(true);
        }
    }
}
