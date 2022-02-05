package com.ajayvijay.bankingapplication.repository;

import com.ajayvijay.bankingapplication.object.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
