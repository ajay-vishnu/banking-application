package com.ajayvijay.bankingapplication.controller;

import com.ajayvijay.bankingapplication.json.SessionJson;
import com.ajayvijay.bankingapplication.object.Session;
import com.ajayvijay.bankingapplication.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/session")
@CrossOrigin
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @PostMapping
    public Session getSessionDetails(@RequestBody SessionJson sessionJson)  {
        return  sessionService.getSession(sessionJson);
    }
}
