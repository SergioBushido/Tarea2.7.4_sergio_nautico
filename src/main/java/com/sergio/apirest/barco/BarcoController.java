package com.sergio.apirest.barco;

import com.sergio.apirest.barco.dto.BarcoRequest;
import com.sergio.apirest.barco.dto.BarcoResponse;
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

    public BarcoController(final BarcoService barcoService) {
        this.barcoService = barcoService;
    }

    @Operation(summary = "Obtener todos los barcos", description = "Retorna todos los barcos disponibles")
    @GetMapping
    public ResponseEntity<List<BarcoResponse>> getAllBarcos() {
        final List<BarcoResponse> barcos = barcoService.findAll();
        return ResponseEntity.ok(barcos);
    }

    @Operation(summary = "Obtener un barco por ID", description = "Retorna un barco específico por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<BarcoResponse> getBarcoById(@PathVariable final Integer id) {
        return barcoService.findById(id)
                .map(barco -> ResponseEntity.ok().body(barco))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo barco", description = "Inserta un nuevo barco en la base de datos")
    @PostMapping
    public ResponseEntity<BarcoResponse> createBarco(@RequestBody final BarcoRequest barco) {
        final BarcoResponse newBarco = barcoService.save(barco);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBarco);
    }

    @Operation(summary = "Actualizar un barco", description = "Actualiza los datos de un barco existente")
    @PutMapping("/{id}")
    public ResponseEntity<BarcoResponse> updateBarco(@PathVariable final Integer id, @RequestBody final BarcoRequest barcoDetails) {
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
    public ResponseEntity<List<BarcoResponse>> getBarcosPorNombre(@RequestParam final String nombre) {
        final List<BarcoResponse> barcos = barcoService.findByNombre(nombre);
        return ResponseEntity.ok(barcos);
    }

    @Operation(summary = "Obtener la cuota total de amarre", description = "Calcula la cuota total de amarre para todos los barcos")
    @GetMapping("/cuotaTotalAmarre")
    public ResponseEntity<Double> getTotalCuotaAmarre() {
        final Double totalCuotaAmarre = barcoService.getTotalCuotaAmarre();
        return ResponseEntity.ok(totalCuotaAmarre);
    }

    // Actualizar la cuota de amarre de un barco por su número de amarre
    @PutMapping("/actualizarCuotaAmarre")
    public ResponseEntity<BarcoResponse> updateCuotaAmarre(@RequestParam final Integer numeroAmarre,
                                                           @RequestParam final Double nuevaCuota) {
        return barcoService.updateCuotaAmarreByNumeroAmarre(numeroAmarre, nuevaCuota)
                .map(barco -> ResponseEntity.ok().body(barco))
                .orElse(ResponseEntity.notFound().build());
    }



}