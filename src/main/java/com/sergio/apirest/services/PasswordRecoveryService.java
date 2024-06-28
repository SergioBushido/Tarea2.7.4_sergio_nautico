/*package com.sergio.apirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.SecureRandom;

@Service
public class PasswordRecoveryService {
    public class UserService {


        private EmailService emailService;

        public void sendPasswordResetEmail(String userEmail) {
            String subject = "Password Reset Request";
            String text = "To reset your password, click the link below:\n" +
                    "http://yourapp.com/reset?token=your-token-here";
            emailService.sendSimpleMessage(userEmail, subject, text);
        }
    }
}*/