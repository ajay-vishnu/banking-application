package com.ajayvijay.bankingapplication.configuration;

import com.ajayvijay.bankingapplication.object.ClientUser;
import com.ajayvijay.bankingapplication.repository.ClientUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Config {
    @Bean
    CommandLineRunner commandLineRunner (ClientUserRepository clientUserRepository) {
        return args -> {
            ClientUser vijay = new ClientUser(
                    "4nn19cs032@nieit.ac.in",
                    "kira",
                    "kira",
                    "Ajay Vishnu"
            );
            ClientUser prajwal = new ClientUser(
                    "4nn19cs022@nieit.ac.in",
                    "prajwalkb",
                    "prajwalkb",
                    "Ajay Vishnu"
            );
            ClientUser ajay = new ClientUser(
                    "4nn19cs005@nieit.ac.in",
                    "ajayv",
                    "ajayv",
                    "Ajay Vishnu"
            );
            ClientUser yashwanth = new ClientUser(
                    "4nn19cs036@nieit.ac.in",
                    "yashu",
                    "yashu",
                    "Ajay Vishnu"
            );
            clientUserRepository.saveAll(List.of(vijay, prajwal, ajay, yashwanth));
        };
    }
}
