package com.ajayvijay.bankingapplication.controller;

import com.ajayvijay.bankingapplication.json.CreditAmountJson;
import com.ajayvijay.bankingapplication.object.CreditAmount;
import com.ajayvijay.bankingapplication.service.CreditAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/creditamount")
public class CreditAmountController {
    @Autowired
    private CreditAmountService creditAmountService;

    @GetMapping(path = "{transactionId}")
    public Optional<CreditAmount> getTransactionsById(@PathVariable("transactionId") Long transactionId)  {
        return creditAmountService.getTransactionById(transactionId);
    }

    @GetMapping(path = "/by/{accountNumber}")
    public List<CreditAmount> getAllTransactionByAccountNumber(@PathVariable("accountNumber") Long accountNumber)   {
        return creditAmountService.getAllTransactions(accountNumber);
    }

    @PostMapping
    public void createNewTransaction(@RequestBody CreditAmountJson creditAmountJson)    {
        creditAmountService.addNewTransaction(creditAmountJson);
    }
}

