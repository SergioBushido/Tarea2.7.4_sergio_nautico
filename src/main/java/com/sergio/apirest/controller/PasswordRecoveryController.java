package com.sergio.apirest.controller;


import com.sergio.apirest.services.PasswordRecoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/password-recovery")
public class PasswordRecoveryController {

    @Autowired
    private PasswordRecoveryService passwordRecoveryService;

    @PostMapping
    public ResponseEntity<String> recoverPassword(@RequestBody PasswordRecoveryRequest request) {
        passwordRecoveryService.recoverPassword(request.getEmail());
        return ResponseEntity.ok("Correo de recuperación enviado.");
    }

    public static class PasswordRecoveryRequest {
        private String email;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}