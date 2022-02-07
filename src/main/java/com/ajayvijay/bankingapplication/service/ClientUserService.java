package com.ajayvijay.bankingapplication.service;

import com.ajayvijay.bankingapplication.json.ClientUserJson;
import com.ajayvijay.bankingapplication.object.ClientUser;
import com.ajayvijay.bankingapplication.repository.ClientUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClientUserService {
    @Autowired
    private ClientUserRepository clientUserRepository;

    public Optional<ClientUser> getClientUser(String username)  {

        return clientUserRepository.findClientUserByUsername(username);
    }

    public Optional<ClientUser> getClientUserByPassword(String username, String password)   {
        return clientUserRepository.findClientUserByUsernameAndPassword(username, password);
    }

    public void addNewClientUser(ClientUserJson clientUserJson) {
        if (clientUserJson.getCreatedBy() != null && clientUserJson.getCreatedBy().length() > 0)    {
            ClientUser clientUser = new ClientUser(
                    clientUserJson.getEmail(),
                    clientUserJson.getUsername(),
                    clientUserJson.getPassword(),
                    clientUserJson.getCreatedBy()
            );
            Optional<ClientUser> clientUserOptional = clientUserRepository.findClientUserByUsername(clientUserJson.getUsername());
            if (clientUserOptional.isPresent()) {
                throw new IllegalStateException("Client User already exists!");
            }
            clientUserRepository.save(clientUser);
        }
        else    {
            throw new IllegalStateException("Must mention the createdBy parameter to update the database.");
        }
    }

    public void deleteClientUser(String username, String deletedBy) {
        if (deletedBy != null && deletedBy.length() > 0)    {
            ClientUser clientUser = clientUserRepository.findClientUserByUsername(username).orElseThrow(() -> new IllegalStateException("Client user with username " + username + " does not exist."));
            clientUser.setUpdatedBy(deletedBy);
            clientUser.setUpdatedAt(LocalDateTime.now());
            clientUser.setDeleted(true);
        }
    }

    @Transactional
    public void updateClientUserPassword(String username, String password, String updatedBy)   {
        if (updatedBy != null && updatedBy.length() > 0)    {
            ClientUser clientUser = clientUserRepository.findClientUserByUsername(username).orElseThrow(() -> new IllegalStateException("User with username " + username + " does not exist."));
            if (password != null && password.length() > 0 && !Objects.equals(clientUser.getPassword(), password))   {
                clientUser.setPassword(password);
                clientUser.setUpdatedBy(updatedBy);
                clientUser.setUpdatedAt(LocalDateTime.now());
            }
        }
        else    {
            throw new IllegalStateException("Must mention the updatedBy name");
        }
    }
}
