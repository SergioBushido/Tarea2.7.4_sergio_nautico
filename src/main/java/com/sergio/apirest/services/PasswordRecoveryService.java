package com.sergio.apirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.SecureRandom;

@Service
public class PasswordRecoveryService {

    @Autowired
    private EmailService emailService;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASSWORD_LENGTH = 8;
    private final SecureRandom random = new SecureRandom();

    public void recoverPassword(String email) {
        String newPassword = generateRandomPassword();
        // Aquí puedes agregar la lógica para guardar la nueva contraseña en la base de datos

        // Enviar el correo electrónico con la nueva contraseña
        try {
            emailService.sendSimpleMessage(email, "Recuperación de contraseña", "Tu nueva contraseña es: " + newPassword);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateRandomPassword() {
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            password.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return password.toString();
    }
}