package com.sergio.apirest.barco;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/barcos")
public class BarcoController {

    private final BarcoService barcoService;

    public BarcoController(BarcoService barcoService) {
        this.barcoService = barcoService;
    }

    @Operation(summary = "Obtener todos los barcos", description = "Retorna todos los barcos disponibles")
    @GetMapping
    public ResponseEntity<List<Barco>> getAllBarcos() {
        List<Barco> barcos = barcoService.findAll();
        return ResponseEntity.ok(barcos);
    }

    @Operation(summary = "Obtener un barco por ID", description = "Retorna un barco específico por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Barco> getBarcoById(@PathVariable Integer id) {
        return barcoService.findById(id)
                .map(barco -> ResponseEntity.ok().body(barco))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo barco", description = "Inserta un nuevo barco en la base de datos")
    @PostMapping
    public ResponseEntity<Barco> createBarco(@RequestBody Barco barco) {
        Barco newBarco = barcoService.save(barco);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBarco);
    }

    @Operation(summary = "Actualizar un barco", description = "Actualiza los datos de un barco existente")
    @PutMapping("/{id}")
    public ResponseEntity<Barco> updateBarco(@PathVariable Integer id, @RequestBody Barco barcoDetails) {
        return barcoService.update(id, barcoDetails)
                .map(barco -> ResponseEntity.ok().body(barco))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un barco", description = "Elimina un barco de la base de datos por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBarco(@PathVariable Integer id) {
        if (barcoService.existsById(id)) {
            barcoService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Buscar barcos por nombre", description = "Retorna una lista de barcos que coinciden con el nombre especificado")
    @GetMapping("/buscar")
    public ResponseEntity<List<Barco>> getBarcosPorNombre(@RequestParam String nombre) {
        List<Barco> barcos = barcoService.findByNombre(nombre);
        return ResponseEntity.ok(barcos);
    }

    @Operation(summary = "Obtener la cuota total de amarre", description = "Calcula la cuota total de amarre para todos los barcos")
    @GetMapping("/cuotaTotalAmarre")
    public ResponseEntity<Double> getTotalCuotaAmarre() {
        Double totalCuotaAmarre = barcoService.getTotalCuotaAmarre();
        return ResponseEntity.ok(totalCuotaAmarre);
    }

    // Actualizar la cuota de amarre de un barco por su número de amarre
    @PutMapping("/actualizarCuotaAmarre")
    public ResponseEntity<Barco> updateCuotaAmarre(@RequestParam Integer numeroAmarre,
                                                   @RequestParam Double nuevaCuota) {
        return barcoService.updateCuotaAmarreByNumeroAmarre(numeroAmarre, nuevaCuota)
                .map(barco -> ResponseEntity.ok().body(barco))
                .orElse(ResponseEntity.notFound().build());
    }



}