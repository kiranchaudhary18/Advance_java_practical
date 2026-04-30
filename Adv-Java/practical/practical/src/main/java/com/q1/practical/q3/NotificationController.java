package com.q1.practical.q3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    private NotificationServiceField notificationServiceField;

    @Autowired
    private NotificationServiceConstructor notificationServiceConstructor;

    @Autowired
    private NotificationServiceSetter notificationServiceSetter;

    @GetMapping("/q3/field")
    public String testFieldInjection() {
        return notificationServiceField.sendEmail("Test from Field Injection");
    }

    @GetMapping("/q3/constructor")
    public String testConstructorInjection() {
        return notificationServiceConstructor.sendEmail("Test from Constructor Injection");
    }

    @GetMapping("/q3/setter")
    public String testSetterInjection() {
        return notificationServiceSetter.sendEmail("Test from Setter Injection");
    }
}
