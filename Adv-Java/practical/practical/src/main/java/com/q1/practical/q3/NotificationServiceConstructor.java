package com.q1.practical.q3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceConstructor {

    private final EmailService emailService;

    @Autowired
    public NotificationServiceConstructor(EmailService emailService) {
        this.emailService = emailService;
    }

    public String sendEmail(String msg) {
        return "Constructor Injection -> " + emailService.sendEmail(msg);
    }
}
