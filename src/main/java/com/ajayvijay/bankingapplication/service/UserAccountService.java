package com.ajayvijay.bankingapplication.service;

import com.ajayvijay.bankingapplication.json.UserAccountJson;
import com.ajayvijay.bankingapplication.object.ClientUser;
import com.ajayvijay.bankingapplication.object.UserAccount;
import com.ajayvijay.bankingapplication.repository.ClientUserRepository;
import com.ajayvijay.bankingapplication.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserAccountService {
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private ClientUserService clientUserService;

    public Optional<UserAccount> getUserAccount(Long accountNumber)   {
        return userAccountRepository.findUserAccountByAccountNumber(accountNumber);
    }

    public Optional<UserAccount> getUserAccountByClientUser(ClientUser clientUser)  {
        return userAccountRepository.findUserAccountByClientUser(clientUser);
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
