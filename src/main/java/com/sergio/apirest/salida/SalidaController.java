package com.sergio.apirest.salida;

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Obtener todas las salidas", description = "Devuelve una lista de todas las salidas registradas")
    @GetMapping
    public ResponseEntity<List<Salida>> getAllSalidas() {
        List<Salida> salidas = salidaService.findAll();
        return ResponseEntity.ok(salidas);
    }

    @Operation(summary = "Obtener una salida por ID", description = "Devuelve una salida específica buscando por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Salida> getSalidaById(@PathVariable Integer id) {
        return salidaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Crear una nueva salida", description = "Crea y registra una nueva salida")
    @PostMapping
    public ResponseEntity<Salida> createSalida(@Validated @RequestBody Salida salida) {
        Salida newSalida = salidaService.save(salida);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSalida);
    }
    @Operation(summary = "Actualizar una salida existente", description = "Actualiza los datos de una salida existente")
    @PutMapping("/{id}")
    public ResponseEntity<Salida> updateSalida(@PathVariable Integer id, @Validated @RequestBody Salida salidaDetails) {
        return salidaService.update(id, salidaDetails)
                .map(updatedSalida -> ResponseEntity.ok().body(updatedSalida))
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Eliminar una salida", description = "Elimina una salida registrada en la base de datos")
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
