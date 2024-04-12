package com.sergio.apirest.socio;

import com.sergio.apirest.barco.Barco;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/socio")
public class SocioController {

    private final SocioService socioService;

    public SocioController(SocioService socioService) {
        this.socioService = socioService;
    }

    @Operation(summary = "Crear un nuevo socio", description = "Crea y registra un nuevo socio en la base de datos")
    @PostMapping
    public ResponseEntity<Socio> createSocio(@Valid @RequestBody Socio socio) {
        Socio createdSocio = socioService.createSocio(socio);
        return new ResponseEntity<>(createdSocio, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener un socio por ID", description = "Devuelve un socio específico por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Socio> getSocioById(@PathVariable Integer id) {
        Socio socio = socioService.getSocioById(id);
        return ResponseEntity.ok(socio);
    }

    @Operation(summary = "Actualizar un socio", description = "Actualiza los datos de un socio existente")
    @PutMapping("/{id}")
    public ResponseEntity<Socio> updateSocio(@PathVariable Integer id, @Valid @RequestBody Socio personDetails) {
        Socio updatedSocio = socioService.updateSocio(id, personDetails);
        return ResponseEntity.ok(updatedSocio);
    }

    @Operation(summary = "Eliminar un socio por ID", description = "Elimina un socio de la base de datos usando su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSocio(@PathVariable Integer id) {
        socioService.deleteSocioById(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Añadir un barco a un socio", description = "Asocia un barco nuevo o existente a un socio")
    @PostMapping("/{socioId}/barcos")
    public ResponseEntity<Barco> addBarcoToSocio(@PathVariable Integer socioId, @RequestBody Barco barco) {
        try {
            Barco addedBarco = socioService.addBarcoToSocio(socioId, barco);
            return new ResponseEntity<>(addedBarco, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            // Manejo de la excepción, por ejemplo, si el socio no existe
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


}