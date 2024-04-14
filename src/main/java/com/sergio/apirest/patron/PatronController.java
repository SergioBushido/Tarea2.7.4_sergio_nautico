package com.sergio.apirest.patron;

import com.sergio.apirest.barco.dto.BarcoResponse;
import com.sergio.apirest.patron.dto.PatronRequest;
import com.sergio.apirest.patron.dto.PatronResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
@AllArgsConstructor
public class PatronController {

    private final PatronService patronService;

    @Operation(summary = "Obtiene todos los patrones", description = "Retorna una lista de todos los patrones disponibles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patrones encontrados exitosamente")
    })
    @GetMapping
    public List<PatronResponse> getAllPatrons() {
        return patronService.findAllPatrones();
    }

    @Operation(summary = "Busca un patrón por ID", description = "Retorna un patrón específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patrón encontrado"),
            @ApiResponse(responseCode = "404", description = "Patrón no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PatronResponse> getPatronById(@PathVariable final Integer id) {
        return patronService.findPatronById(id)
                .map(patron -> ResponseEntity.ok().body(patron))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crea un nuevo patrón", description = "Guarda y retorna el patrón creado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Patrón creado exitosamente")
    })
    @PostMapping
    public ResponseEntity<PatronResponse> createPatron(@RequestBody final PatronRequest patronRequest) {
        final PatronResponse newPatron = patronService.savePatron(patronRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPatron);
    }

    @Operation(summary = "Actualiza un patrón existente", description = "Actualiza y retorna el patrón especificado por el ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patrón actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Patrón no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PatronResponse> updatePatron(@PathVariable final Integer id, @RequestBody final PatronRequest patronRequest) {
        return patronService.updatePatron(id, patronRequest)
                .map(patron -> ResponseEntity.ok().body(patron))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Elimina un patrón por su ID", description = "Elimina el patrón especificado por el ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patrón eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Patrón no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable final Integer id) {
        boolean isDeleted = patronService.deletePatron(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
