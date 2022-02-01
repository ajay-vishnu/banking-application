package com.ajayvijay.bankingapplication.repository;

import com.ajayvijay.bankingapplication.object.DebitAmount;
import com.ajayvijay.bankingapplication.object.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DebitAmountRepository extends JpaRepository<DebitAmount, Long> {
    @Query("select d from DebitAmount d where d.isDeleted=false and d.userAccount=?1")
    List<DebitAmount> findAllByAccountNumber(UserAccount userAccount);

    @Query("select d from DebitAmount d where d.isDeleted=false and d.transactionId=?1")
    Optional<DebitAmount> findByTransactionId(Long transactionId);
}
