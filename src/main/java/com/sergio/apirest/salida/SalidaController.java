package com.sergio.apirest.salida;

import com.sergio.apirest.salida.dto.SalidaRequest;
import com.sergio.apirest.salida.dto.SalidaResponse;
import com.sergio.apirest.salida.dto.SalidaRequestMapper;
import com.sergio.apirest.salida.dto.SalidaResponseMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/salidas")
@Api(value = "SalidaController", description = "Controlador para la gestión de salidas de barcos")
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
    @GetMapping
    @ApiOperation(value = "Obtiene todas las salidas", notes = "Devuelve una lista de todas las salidas registradas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Salidas encontradas exitosamente")
    })
    public ResponseEntity<List<SalidaResponse>> getAllSalidas() {
        List<Salida> salidas = salidaService.findAll();
        List<SalidaResponse> salidaResponses = salidas.stream()
                .map(salidaResponseMapper::entityToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(salidaResponses);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una salida por su ID", notes = "Devuelve una salida específica por su ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Salida encontrada"),
            @ApiResponse(code = 404, message = "Salida no encontrada")
    })
    public ResponseEntity<SalidaResponse> getSalidaById(@PathVariable Integer id) {
        return salidaService.findById(id)
                .map(salida -> ResponseEntity.ok(salidaResponseMapper.entityToResponse(salida)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ApiOperation(value = "Crea una nueva salida", notes = "Guarda y retorna los detalles de la nueva salida creada")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Salida creada exitosamente"),
    })
    public ResponseEntity<SalidaResponse> createSalida(@RequestBody SalidaRequest salidaRequest) {
        Salida salida = salidaRequestMapper.dtoToEntity(salidaRequest);
        Salida savedSalida = salidaService.save(salida);
        return new ResponseEntity<>(salidaResponseMapper.entityToResponse(savedSalida), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Actualiza una salida existente", notes = "Actualiza y retorna la salida especificada por el ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Salida actualizada exitosamente"),
            @ApiResponse(code = 404, message = "Salida no encontrada")
    })
    public ResponseEntity<SalidaResponse> updateSalida(@PathVariable Integer id, @RequestBody SalidaRequest salidaRequest) {
        return salidaService.update(id, salidaRequestMapper.dtoToEntity(salidaRequest))
                .map(updatedSalida -> ResponseEntity.ok(salidaResponseMapper.entityToResponse(updatedSalida)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Elimina una salida por su ID", notes = "Elimina la salida especificada por el ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Salida eliminada exitosamente"),
            @ApiResponse(code = 404, message = "Salida no encontrada")
    })
    public ResponseEntity<Void> deleteSalida(@PathVariable Integer id) {
        if (salidaService.existsById(id)) {
            salidaService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
