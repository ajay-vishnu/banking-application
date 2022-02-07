package com.ajayvijay.bankingapplication.repository;

import com.ajayvijay.bankingapplication.object.ClientUser;
import com.ajayvijay.bankingapplication.object.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    @Query("select s from Session s where s.isDeleted=false and s.clientUser=?1")
    Optional<Session> findByClientUser(ClientUser clientUser);
}
