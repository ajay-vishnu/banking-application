package com.ajayvijay.bankingapplication.repository;

import com.ajayvijay.bankingapplication.object.ClientUser;
import com.ajayvijay.bankingapplication.object.Session;
import com.ajayvijay.bankingapplication.object.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    @Query("select s from Session s where s.isDeleted=false and s.userAccount=?1")
    Optional<Session> findByUserAccount(UserAccount userAccount);
}
