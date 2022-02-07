package com.ajayvijay.bankingapplication.repository;

import com.ajayvijay.bankingapplication.object.CreditAmount;
import com.ajayvijay.bankingapplication.object.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CreditAmountRepository extends JpaRepository<CreditAmount, Double> {

    @Query("select c from CreditAmount c where c.isDeleted=false and c.userAccount=?1")
    List<CreditAmount> findAllByAccountNumber(UserAccount accountNumber);

    @Query("select c from CreditAmount c where c.isDeleted=false and c.transactionId=?1")
    Optional<CreditAmount> findByTransactionId(String transactionId);
}
