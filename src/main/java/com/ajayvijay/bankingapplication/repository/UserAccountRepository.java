package com.ajayvijay.bankingapplication.repository;

import com.ajayvijay.bankingapplication.object.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    @Query("select u from UserAccount u where u.isDeleted=false and u.accountNumber=?1")
    Optional<UserAccount> findUserAccountByAccountNumber(Long accountNumber);
}
