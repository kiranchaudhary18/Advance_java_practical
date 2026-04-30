package com.q1.practical.q3;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public String sendEmail(String msg) {
        return "Email Sent Successfully: " + msg;
    }
}
