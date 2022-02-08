package com.ajayvijay.bankingapplication.service;

import com.ajayvijay.bankingapplication.json.UserAccountJson;
import com.ajayvijay.bankingapplication.object.ClientUser;
import com.ajayvijay.bankingapplication.object.UserAccount;
import com.ajayvijay.bankingapplication.repository.ClientUserRepository;
import com.ajayvijay.bankingapplication.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserAccountService {
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private ClientUserService clientUserService;

    @Autowired
    private EmailSenderService emailSenderService;

    public Optional<UserAccount> getUserAccount(Long accountNumber)   {
        return userAccountRepository.findUserAccountByAccountNumber(accountNumber);
    }

    public Optional<UserAccount> getUserAccountByClientUser(ClientUser clientUser)  {
        return userAccountRepository.findUserAccountByClientUser(clientUser);
    }

    @Transactional
    public void updateUserAccountBalance(UserAccount newUserAccount)   {
        UserAccount userAccount = userAccountRepository.findUserAccountByAccountNumber(newUserAccount.getAccountNumber()).orElseThrow(() -> new IllegalStateException("User account number is invalid."));
        userAccount.setAccountBalance(newUserAccount.getAccountBalance());
    }

    public void addNewAccountUser(UserAccountJson userAccountJson)  {
        if (userAccountJson.getUsername() != null && userAccountJson.getUsername().length() > 0) {
            Optional<ClientUser> clientUserOptional = clientUserService.getClientUserByPassword(userAccountJson.getUsername(), userAccountJson.getPassword());
            ClientUser clientUser = null;
            if (!clientUserOptional.isPresent()) {
                clientUser = new ClientUser(
                        userAccountJson.getEmail(),
                        userAccountJson.getUsername(),
                        userAccountJson.getPassword(),
                        userAccountJson.getUsername()
                );
                clientUserService.addNewClientUser(clientUser);
            }
            clientUser = clientUserService.getClientUser(userAccountJson.getUsername()).orElseThrow(() -> new IllegalStateException("Username doesn't exist."));
            UserAccount userAccount = new UserAccount(
                    userAccountJson.getHolderName(),
                    userAccountJson.getPhone(),
                    userAccountJson.getHolderAddress(),
                    userAccountJson.getGovernmentId(),
                    userAccountJson.getAccountBalance(),
                    clientUser,
                    userAccountJson.getUsername()
            );
            userAccountRepository.save(userAccount);
            emailSenderService.sendSimpleEmail(
                    clientUser.getEmail(),
                    userAccountJson.getHolderName()
            );
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
