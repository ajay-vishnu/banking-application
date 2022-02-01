package com.ajayvijay.bankingapplication.configuration;

import com.ajayvijay.bankingapplication.object.ClientUser;
import com.ajayvijay.bankingapplication.object.UserAccount;
import com.ajayvijay.bankingapplication.repository.ClientUserRepository;
import com.ajayvijay.bankingapplication.repository.CreditAmountRepository;
import com.ajayvijay.bankingapplication.repository.UserAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Config {
    @Bean
    CommandLineRunner commandLineRunner (ClientUserRepository clientUserRepository,
                                         UserAccountRepository userAccountRepository,
                                         CreditAmountRepository creditAmountRepository) {
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
            UserAccount vijayu = new UserAccount(
                    1001001L,
                    "Vijay Biradar",
                    "7769034526",
                    "Mysuru, Karnataka",
                    "432456789876",
                    20324.56,
                    "Ajay Vishnu"
            );
            UserAccount prajwalu = new UserAccount(
                    1001002L,
                    "Prajwal K B",
                    "5986234526",
                    "Mandya, Karnataka",
                    "153875924682",
                    20987.56,
                    "Ajay Vishnu"
            );
            UserAccount ajayu = new UserAccount(
                    1001003L,
                    "Ajay Vishnu V",
                    "7769456321",
                    "Davangere, Karnataka",
                    "785432569731",
                    20444.56,
                    "Vijay Biradar"
            );
            UserAccount yashwanthu = new UserAccount(
                    1001004L,
                    "Yashwanth M R",
                    "2597534526",
                    "Mysuru, Karnataka",
                    "468279137532",
                    20678.56,
                    "Vijay Biradar"
            );
            userAccountRepository.saveAll(List.of(vijayu, prajwalu, ajayu, yashwanthu));
        };
    }
}
