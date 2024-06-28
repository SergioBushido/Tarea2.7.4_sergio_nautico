/*package com.sergio.apirest.controller;

import com.sergio.apirest.services.EmailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "test", description = "mail")
@RestController
@RequestMapping("/api/v1/t")
public class TestController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/test-email")
    public String testEmail() {
        emailService.sendSimpleMessage("sergio.ume@yourdomain.com", "Test Subject", "Test email content");
        return "Email sent";
    }
}
*/