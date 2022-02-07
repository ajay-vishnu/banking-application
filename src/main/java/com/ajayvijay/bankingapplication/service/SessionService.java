package com.ajayvijay.bankingapplication.service;

import com.ajayvijay.bankingapplication.json.SessionJson;
import com.ajayvijay.bankingapplication.object.ClientUser;
import com.ajayvijay.bankingapplication.object.Session;
import com.ajayvijay.bankingapplication.object.UserAccount;
import com.ajayvijay.bankingapplication.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private ClientUserService clientUserService;

    @Autowired
    private UserAccountService userAccountService;

    @Transactional
    public Session getSession(SessionJson sessionJson)    {
        ClientUser clientUser = clientUserService.getClientUserByPassword(sessionJson.getUsername(), sessionJson.getPassword()).orElseThrow(() -> new IllegalStateException("Wrong username or password."));
        UserAccount userAccount = userAccountService.getUserAccountByClientUser(clientUser).orElseThrow(() -> new IllegalStateException("User account does not exist."));
        Optional<Session> sessionOptional = sessionRepository.findByUserAccount(userAccount);
        if (sessionOptional.isPresent()) {
            return sessionOptional.get();
        }
        else {
            String sessionId = clientUser.getUsername() + System.currentTimeMillis();
            Session session = new Session(
                    userAccount,
                    sessionId
            );
            sessionRepository.save(session);
            return session;
        }
    }
}
