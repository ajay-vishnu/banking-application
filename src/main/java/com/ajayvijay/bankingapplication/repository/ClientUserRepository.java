package com.ajayvijay.bankingapplication.repository;

import com.ajayvijay.bankingapplication.object.ClientUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientUserRepository extends JpaRepository<ClientUser, Long> {
    @Query("select c from ClientUser c where c.isDeleted=false and c.username=?1")
    Optional<ClientUser> findClientUserByUsername(String username);

    @Query("select c from ClientUser c where c.isDeleted=false and c.username=?1 and c.password=?1")
    Optional<ClientUser> findClientUserByUsernameAndPassword(String username, String password);
}
