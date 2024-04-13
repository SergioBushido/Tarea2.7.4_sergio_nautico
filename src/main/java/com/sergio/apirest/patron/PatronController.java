package com.sergio.apirest.patron;

import com.sergio.apirest.patron.dto.PatronRequest;
import com.sergio.apirest.patron.dto.PatronResponse;
import com.sergio.apirest.patron.dto.PatronRequestMapper;
import com.sergio.apirest.patron.dto.PatronResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/patrones")
public class PatronController {

    private final PatronService patronService;
    private final PatronRequestMapper patronRequestMapper;
    private final PatronResponseMapper patronResponseMapper;

    @Autowired
    public PatronController(PatronService patronService,
                            PatronRequestMapper patronRequestMapper,
                            PatronResponseMapper patronResponseMapper) {
        this.patronService = patronService;
        this.patronRequestMapper = patronRequestMapper;
        this.patronResponseMapper = patronResponseMapper;
    }

    @Operation(summary = "Obtener todos los patrones", description = "Devuelve una lista de todos los patrones disponibles")
    @GetMapping
    public ResponseEntity<List<PatronResponse>> getAllPatrones() {
        List<Patron> patrones = patronService.findAllPatrones();
        List<PatronResponse> responses = patrones.stream()
                .map(patronResponseMapper::entityToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @Operation(summary = "Obtener un patrón por ID", description = "Devuelve un patrón específico por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<PatronResponse> getPatronById(@PathVariable Integer id) {
        return patronService.findPatronById(id)
                .map(patron -> ResponseEntity.ok(patronResponseMapper.entityToResponse(patron)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Crear un nuevo patrón", description = "Crea un nuevo patrón y lo agrega a la base de datos")
    @PostMapping
    public ResponseEntity<PatronResponse> createPatron(@RequestBody PatronRequest patronRequest) {
        Patron patron = patronRequestMapper.dtoToEntity(patronRequest);
        Patron newPatron = patronService.savePatron(patron);
        PatronResponse response = patronResponseMapper.entityToResponse(newPatron);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un patrón existente", description = "Actualiza los datos de un patrón existente")
    @PutMapping("/{id}")
    public ResponseEntity<PatronResponse> updatePatron(@PathVariable Integer id, @RequestBody PatronRequest patronRequest) {
        return patronService.findPatronById(id)
                .map(existingPatron -> {
                    patronRequestMapper.updateEntityFromDto(patronRequest, existingPatron);
                    Patron updatedPatron = patronService.savePatron(existingPatron);
                    return ResponseEntity.ok(patronResponseMapper.entityToResponse(updatedPatron));
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Eliminar un patrón", description = "Elimina un patrón de la base de datos")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Integer id) {
        if (patronService.deletePatron(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
