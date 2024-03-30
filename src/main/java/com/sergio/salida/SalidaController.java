package com.sergio.salida;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/salidas")
public class SalidaController {

    private final SalidaService salidaService;

    public SalidaController(SalidaService salidaService) {
        this.salidaService = salidaService;
    }

    @GetMapping
    public ResponseEntity<List<Salida>> getAllSalidas() {
        List<Salida> salidas = salidaService.findAll();
        return ResponseEntity.ok(salidas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Salida> getSalidaById(@PathVariable Integer id) {
        return salidaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Salida> createSalida(@RequestBody Salida salida) {
        Salida newSalida = salidaService.save(salida);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSalida);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Salida> updateSalida(@PathVariable Integer id, @Validated @RequestBody Salida salidaDetails) {
        return salidaService.update(id, salidaDetails)
                .map(updatedSalida -> ResponseEntity.ok().body(updatedSalida))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalida(@PathVariable Integer id) {
        if (salidaService.existsById(id)) {
            salidaService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}