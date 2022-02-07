package com.ajayvijay.bankingapplication.controller;

import com.ajayvijay.bankingapplication.json.SessionJson;
import com.ajayvijay.bankingapplication.object.Session;
import com.ajayvijay.bankingapplication.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/session")
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @GetMapping
    public Session getSessionDetails(@RequestBody SessionJson sessionJson)  {
        return  sessionService.getSession(sessionJson);
    }
}
