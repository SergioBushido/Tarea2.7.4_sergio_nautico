package com.sergio.apirest.barco;

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

    @GetMapping
    public ResponseEntity<List<Barco>> getAllBarcos() {
        List<Barco> barcos = barcoService.findAll();
        return ResponseEntity.ok(barcos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Barco> getBarcoById(@PathVariable Integer id) {
        return barcoService.findById(id)
                .map(barco -> ResponseEntity.ok().body(barco))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Barco> createBarco(@RequestBody Barco barco) {
        Barco newBarco = barcoService.save(barco);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBarco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Barco> updateBarco(@PathVariable Integer id, @RequestBody Barco barcoDetails) {
        return barcoService.update(id, barcoDetails)
                .map(barco -> ResponseEntity.ok().body(barco))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBarco(@PathVariable Integer id) {
        if (barcoService.existsById(id)) {
            barcoService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Obtener barcos por nombre
    @GetMapping("/buscar")
    public ResponseEntity<List<Barco>> getBarcosPorNombre(@RequestParam String nombre) {
        List<Barco> barcos = barcoService.findByNombre(nombre);
        return ResponseEntity.ok(barcos);
    }

    // Obtener la cuota total de amarre de todos los barcos
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