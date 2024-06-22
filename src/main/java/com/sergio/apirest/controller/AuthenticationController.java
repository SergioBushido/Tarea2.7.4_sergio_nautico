package com.sergio.apirest.controller;

import com.sergio.apirest.services.AuthenticationService;
import com.sergio.apirest.user.auth.AuthenticationRequest;
import com.sergio.apirest.user.auth.AuthenticationResponse;
import com.sergio.apirest.user.auth.RegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Tag(name = "AuthenticationController", description = "Controlador para operaciones relacionadas con authenticación de usuarios")

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Registrar un usuario nuevo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario registrado exitosamente",
                    content = @Content(schema = @Schema(implementation = RegisterRequest.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no registrado")
    })
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @Operation(summary = "Authenticate un usuario registrado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario authenticado exitosamente",
                    content = @Content(schema = @Schema(implementation = AuthenticationRequest.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no authentificado",
                    content = @Content(schema = @Schema(implementation = AuthenticationResponse.class)))
    })
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @Operation(summary = "Refrescar el token de un usuario autenticado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario registrado exitosamente",
                    content = @Content(schema = @Schema(implementation = HttpServletRequest.class))),
            @ApiResponse(responseCode = "404", description = "Token no refrescado")
    })
    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authenticationService.refreshToken(request, response);
    }

}