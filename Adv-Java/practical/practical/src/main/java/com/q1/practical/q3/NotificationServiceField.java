package com.q1.practical.q3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceField {

    @Autowired
    private EmailService emailService;

    public String sendEmail(String msg) {
        return "Field Injection -> " + emailService.sendEmail(msg);
    }
}
