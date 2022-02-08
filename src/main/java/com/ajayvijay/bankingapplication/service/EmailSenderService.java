package com.ajayvijay.bankingapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSimpleEmail(String toEmail, String name) {
        String subject = "Welcome " + name + " for the banking application.";
        String body = "Welcome " + name + " for the banking application.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sherlockbankapp@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        javaMailSender.send(message);
    }
}
