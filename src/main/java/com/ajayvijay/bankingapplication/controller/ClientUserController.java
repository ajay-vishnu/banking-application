package com.ajayvijay.bankingapplication.controller;

import com.ajayvijay.bankingapplication.json.ClientUserJson;
import com.ajayvijay.bankingapplication.object.ClientUser;
import com.ajayvijay.bankingapplication.service.ClientUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/clientUser")
public class ClientUserController {
    @Autowired
    private ClientUserService clientUserService;

    @GetMapping(path = "{username}")
    public Optional<ClientUser> getUserDetails(@PathVariable("username") String username)  {
        return clientUserService.getClientUser(username);
    }

    @PostMapping
    public void registerNewClientUser(@RequestBody ClientUserJson clientUser)   {
        clientUserService.addNewClientUser(clientUser);
    }

    @DeleteMapping(path = "{username}")
    public void deleteClientUser(@PathVariable("username") String username,
                                 @RequestParam String sessionId) {
        clientUserService.deleteClientUser(username, sessionId);
    }

    @PutMapping(path = "{username}")
    public void updateClientUserPassword(@PathVariable("username") String username,
                                         @RequestParam String sessionId,
                                         @RequestParam String password) {
        clientUserService.updateClientUserPassword(username, password, sessionId);
    }
}
