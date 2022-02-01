package com.ajayvijay.bankingapplication.controller;

import com.ajayvijay.bankingapplication.json.DebitAmountJson;
import com.ajayvijay.bankingapplication.object.DebitAmount;
import com.ajayvijay.bankingapplication.service.DebitAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/debitAmount")
public class DebitAmountController {
    @Autowired
    private DebitAmountService debitAmountService;

    @GetMapping(path = "{transactionId}")
    public Optional<DebitAmount> getTransactionsById(@PathVariable("transactionId") Long transactionId)  {
        return debitAmountService.getTransactionById(transactionId);
    }

    @GetMapping(path = "/by/{accountNumber}")
    public List<DebitAmount> getAllTransactionByAccountNumber(@PathVariable("accountNumber") Long accountNumber)   {
        return debitAmountService.getAllTransactions(accountNumber);
    }

    @PostMapping
    public void createNewTransaction(@RequestBody DebitAmountJson debitAmountJson)    {
        debitAmountService.addNewTransaction(debitAmountJson);
    }
}
