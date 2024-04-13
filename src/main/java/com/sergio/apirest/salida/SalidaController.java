package com.sergio.apirest.salida;

import com.sergio.apirest.salida.dto.SalidaRequest;
import com.sergio.apirest.salida.dto.SalidaResponse;
import com.sergio.apirest.salida.dto.SalidaRequestMapper;
import com.sergio.apirest.salida.dto.SalidaResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/salidas")
public class SalidaController {

    private final SalidaService salidaService;
    private final SalidaRequestMapper salidaRequestMapper;
    private final SalidaResponseMapper salidaResponseMapper;

    public SalidaController(SalidaService salidaService,
                            SalidaRequestMapper salidaRequestMapper,
                            SalidaResponseMapper salidaResponseMapper) {
        this.salidaService = salidaService;
        this.salidaRequestMapper = salidaRequestMapper;
        this.salidaResponseMapper = salidaResponseMapper;
    }

    @Operation(summary = "Obtener todas las salidas", description = "Devuelve una lista de todas las salidas registradas")
    @GetMapping
    public ResponseEntity<List<SalidaResponse>> getAllSalidas() {
        List<Salida> salidas = salidaService.findAll();
        List<SalidaResponse> salidaResponses = salidas.stream()
                .map(salidaResponseMapper::entityToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(salidaResponses);
    }

    @Operation(summary = "Obtener una salida por ID", description = "Devuelve una salida específica buscando por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<SalidaResponse> getSalidaById(@PathVariable Integer id) {
        return salidaService.findById(id)
                .map(salida -> ResponseEntity.ok(salidaResponseMapper.entityToResponse(salida)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva salida", description = "Crea y registra una nueva salida")
    @PostMapping
    public ResponseEntity<SalidaResponse> createSalida(@Validated @RequestBody SalidaRequest salidaRequest) {
        Salida salida = salidaRequestMapper.dtoToEntity(salidaRequest);
        Salida newSalida = salidaService.save(salida);
        SalidaResponse salidaResponse = salidaResponseMapper.entityToResponse(newSalida);
        return ResponseEntity.status(HttpStatus.CREATED).body(salidaResponse);
    }

    @Operation(summary = "Actualizar una salida existente", description = "Actualiza los datos de una salida existente")
    @PutMapping("/{id}")
    public ResponseEntity<SalidaResponse> updateSalida(@PathVariable Integer id,
                                                       @Validated @RequestBody SalidaRequest salidaRequest) {
        return salidaService.findById(id).map(existingSalida -> {
            salidaRequestMapper.updateEntityFromDto(salidaRequest, existingSalida);
            Salida updatedSalida = salidaService.save(existingSalida);
            SalidaResponse salidaResponse = salidaResponseMapper.entityToResponse(updatedSalida);
            return ResponseEntity.ok().body(salidaResponse);
        }).orElse(ResponseEntity.notFound().build());
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
