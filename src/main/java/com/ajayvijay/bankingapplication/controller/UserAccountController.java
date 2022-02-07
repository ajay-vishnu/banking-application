package com.ajayvijay.bankingapplication.controller;

import com.ajayvijay.bankingapplication.json.UserAccountJson;
import com.ajayvijay.bankingapplication.object.UserAccount;
import com.ajayvijay.bankingapplication.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/userAccount")
public class UserAccountController {
    @Autowired
    private UserAccountService userAccountService;

    @GetMapping(path = "{accountNumber}")
    public Optional<UserAccount> getUserAccountDetails(@PathVariable("accountNumber") Long accountNumber)   {
        return userAccountService.getUserAccount(accountNumber);
    }

    @PostMapping
    public void registerNewUserAccount(@RequestBody UserAccountJson userAccountJson)    {
        userAccountService.addNewAccountUser(userAccountJson);
    }

    @DeleteMapping(path = "{accountNumber}")
    public void deleteUserAccount(@PathVariable("accountNumber") Long accountNumber,
                                  @RequestParam String deletedBy)   {
        userAccountService.deleteUserAccount(accountNumber, deletedBy);
    }
    
}
