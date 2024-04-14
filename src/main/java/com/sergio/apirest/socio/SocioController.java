package com.sergio.apirest.socio;

import com.sergio.apirest.barco.Barco;
import com.sergio.apirest.socio.dto.SocioRequest;
import com.sergio.apirest.socio.dto.SocioRequestMapper;
import com.sergio.apirest.socio.dto.SocioResponse;
import com.sergio.apirest.socio.dto.SocioResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/socios")
public class SocioController {

    private final SocioService socioService;
    private final SocioRequestMapper socioRequestMapper;
    private final SocioResponseMapper socioResponseMapper;

    @Autowired
    public SocioController(SocioService socioService,
                           SocioRequestMapper socioRequestMapper,
                           SocioResponseMapper socioResponseMapper) {
        this.socioService = socioService;
        this.socioRequestMapper = socioRequestMapper;
        this.socioResponseMapper = socioResponseMapper;
    }

    @Operation(summary = "Crea un nuevo socio", description = "Registra un nuevo socio en el club")
    @ApiResponse(responseCode = "201", description = "Socio creado exitosamente")
    @PostMapping
    public ResponseEntity<SocioResponse> createSocio(@RequestBody SocioRequest socioRequest) {
        Socio socio = socioRequestMapper.dtoToEntity(socioRequest);
        Socio createdSocio = socioService.createSocio(socio);
        SocioResponse socioResponse = socioResponseMapper.entityToResponse(createdSocio);
        return new ResponseEntity<>(socioResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtiene un socio por su ID", description = "Devuelve los datos de un socio específico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Socio encontrado"),
            @ApiResponse(responseCode = "404", description = "Socio no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SocioResponse> getSocioById(@PathVariable Integer id) {
        Socio socio = socioService.getSocioById(id);
        if (socio != null) {
            SocioResponse socioResponse = socioResponseMapper.entityToResponse(socio);
            return ResponseEntity.ok(socioResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Actualiza los datos de un socio", description = "Modifica los datos del socio especificado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Socio actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Socio no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<SocioResponse> updateSocio(@PathVariable Integer id, @RequestBody SocioRequest socioRequest) {
        Socio socioDetails = socioRequestMapper.dtoToEntity(socioRequest);
        Socio updatedSocio = socioService.updateSocio(id, socioDetails);
        SocioResponse socioResponse = socioResponseMapper.entityToResponse(updatedSocio);
        return ResponseEntity.ok(socioResponse);
    }

    @Operation(summary = "Elimina un socio", description = "Elimina los datos del socio especificado del club")
    @ApiResponse(responseCode = "200", description = "Socio eliminado exitosamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSocioById(@PathVariable Integer id) {
        try {
            socioService.deleteSocioById(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Añade un barco a un socio", description = "Asocia un nuevo barco al socio especificado")
    @ApiResponse(responseCode = "201", description = "Barco añadido al socio exitosamente")
    @PostMapping("/{socioId}/barcos")
    public ResponseEntity<Barco> addBarcoToSocio(@PathVariable Integer socioId, @RequestBody Barco newBarco) {
        Barco barco = socioService.addBarcoToSocio(socioId, newBarco);
        return new ResponseEntity<>(barco, HttpStatus.CREATED);
    }
}
