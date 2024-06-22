package com.sergio.apirest.socio;

import com.sergio.apirest.barco.Barco;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;


@Tag(name = "SocioController", description = "Controlador para operaciones relacionadas con socios")
@RestController
@RequestMapping("/api/v1/socios")
public class SocioController {

    private final SocioService socioService;

    public SocioController(SocioService socioService) {
        this.socioService = socioService;
    }

    // Crear
    @PostMapping
    public ResponseEntity<Socio> createSocio(@Valid @RequestBody Socio socio) {
        Socio createdSocio = socioService.createSocio(socio);
        return new ResponseEntity<>(createdSocio, HttpStatus.CREATED);
    }

    // Mostrar
    @GetMapping("/{id}")
    public ResponseEntity<Socio> getSocioById(@PathVariable Integer id) {
        Socio socio = socioService.getSocioById(id);
        return ResponseEntity.ok(socio);
    }

    // Método para obtener todos los socios
    @Operation(summary = "Listar todos los socios")
    @GetMapping
    public ResponseEntity<List<Socio>> getAllSocios() {
        List<Socio> socios = socioService.getAllSocios();
        return ResponseEntity.ok(socios);
    }


    // Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Socio> updateSocio(@PathVariable Integer id, @Valid @RequestBody Socio personDetails) {
        Socio updatedSocio = socioService.updateSocio(id, personDetails);
        return ResponseEntity.ok(updatedSocio);
    }

    // Método para eliminar por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSocio(@PathVariable Integer id) {
        socioService.deleteSocioById(id);
        return ResponseEntity.noContent().build();
    }


    //insertar un barco a un socio por su id
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