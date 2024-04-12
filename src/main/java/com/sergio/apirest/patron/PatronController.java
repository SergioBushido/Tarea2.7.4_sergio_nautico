package com.sergio.apirest.patron;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patrones") // Asegúrate de que esta ruta coincida con la que intentas acceder
public class PatronController {

    private final PatronService patronService;

    @Autowired
    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @Operation(summary = "Obtener todos los patrones", description = "Devuelve una lista de todos los patrones disponibles")
    @GetMapping
    public List<Patron> getAllPatrones() {
        return patronService.findAllPatrones();
    }

    @Operation(summary = "Obtener un patrón por ID", description = "Devuelve un patrón específico por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable Integer id) {
        return patronService.findPatronById(id)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Crear un nuevo patrón", description = "Crea un nuevo patrón y lo agrega a la base de datos")
    @PostMapping
    public ResponseEntity<Patron> createPatron(@RequestBody Patron patron) {
        Patron newPatron = patronService.savePatron(patron);
        return new ResponseEntity<>(newPatron, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un patrón existente", description = "Actualiza los datos de un patrón existente")
    @PutMapping("/{id}")
    public ResponseEntity<Patron> updatePatron(@PathVariable Integer id, @RequestBody Patron patron) {
        return new ResponseEntity<>(patronService.updatePatron(id, patron), HttpStatus.OK);
    }

    @Operation(summary = "Eliminar un patrón", description = "Elimina un patrón de la base de datos")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Integer id) {
        patronService.deletePatron(id);
        return ResponseEntity.noContent().build();
    }
}
