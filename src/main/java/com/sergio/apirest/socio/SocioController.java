package com.sergio.apirest.socio;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/socio")
public class SocioController {

    private final SocioService socioService;

    public SocioController(SocioService socioService) {
        this.socioService = socioService;
    }

    // Crear
    @PostMapping
    public ResponseEntity<Socio> createSocio(@Valid @RequestBody Socio socio){
        Socio createdSocio = socioService.createSocio(socio);
        return new ResponseEntity<>(createdSocio, HttpStatus.CREATED);
    }

    // Mostrar
    @GetMapping("/{id}")
    public ResponseEntity<Socio> getSocioById(@PathVariable Integer id) {
        Socio socio = socioService.getSocioById(id);
        return ResponseEntity.ok(socio);
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
}
