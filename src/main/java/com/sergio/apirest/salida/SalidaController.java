package com.sergio.apirest.salida;

import com.sergio.apirest.salida.dto.SalidaRequest;
import com.sergio.apirest.salida.dto.SalidaResponse;
import com.sergio.apirest.salida.dto.SalidaRequestMapper;
import com.sergio.apirest.salida.dto.SalidaResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/api/salidas")
public class SalidaController {

    private final SalidaService salidaService;
    private final SalidaRequestMapper salidaRequestMapper;
    private final SalidaResponseMapper salidaResponseMapper;

    @Autowired
    public SalidaController(SalidaService salidaService, SalidaRequestMapper salidaRequestMapper, SalidaResponseMapper salidaResponseMapper) {
        this.salidaService = salidaService;
        this.salidaRequestMapper = salidaRequestMapper;
        this.salidaResponseMapper = salidaResponseMapper;
    }

    @Operation(summary = "Obtiene todas las salidas", description = "Devuelve una lista de todas las salidas registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salidas encontradas exitosamente")
    })
    @GetMapping
    public ResponseEntity<List<SalidaResponse>> getAllSalidas() {
        List<SalidaResponse> salidas = salidaService.findAll();
        return ResponseEntity.ok(salidas);
    }

    @Operation(summary = "Obtiene una salida por su ID", description = "Devuelve una salida específica por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salida encontrada"),
            @ApiResponse(responseCode = "404", description = "Salida no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SalidaResponse> getSalidaById(@PathVariable final Integer id) {
        return salidaService.findById(id)
                .map(salida -> ResponseEntity.ok().body(salida))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crea una nueva salida", description = "Guarda y retorna los detalles de la nueva salida creada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Salida creada exitosamente")
    })
    @PostMapping
    public ResponseEntity<SalidaResponse> createSalida(@RequestBody final SalidaRequest salidaRequest) {
        SalidaResponse savedSalida = salidaService.save(salidaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSalida);
    }

    @Operation(summary = "Actualiza una salida existente", description = "Actualiza y retorna la salida especificada por el ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salida actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Salida no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<SalidaResponse> updateSalida(@PathVariable Integer id, @RequestBody SalidaRequest salidaRequest) {
        return salidaService.update(id, salidaRequestMapper.dtoToEntity(salidaRequest))
                .map(updatedSalida -> ResponseEntity.ok(salidaResponseMapper.entityToResponse(updatedSalida)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Elimina una salida por su ID", description = "Elimina la salida especificada por el ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salida eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Salida no encontrada")
    })
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
