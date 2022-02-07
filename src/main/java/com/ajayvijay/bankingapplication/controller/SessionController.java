package com.ajayvijay.bankingapplication.controller;

import com.ajayvijay.bankingapplication.json.SessionJson;
import com.ajayvijay.bankingapplication.object.Session;
import com.ajayvijay.bankingapplication.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class SessionController {
    @Autowired
    SessionService sessionService;

    @GetMapping
    public Session getSessionDetails(@RequestBody SessionJson sessionJson)  {
        return  sessionService.getSession(sessionJson);
    }
}
