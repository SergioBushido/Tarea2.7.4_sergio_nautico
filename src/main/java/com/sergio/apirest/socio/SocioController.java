package com.sergio.apirest.socio;

import com.sergio.apirest.barco.Barco;
import com.sergio.apirest.socio.dto.SocioRequest;
import com.sergio.apirest.socio.dto.SocioResponse;
import com.sergio.apirest.socio.dto.SocioRequestMapper;
import com.sergio.apirest.socio.dto.SocioResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/socio")
public class SocioController {

    private final SocioService socioService;
    private final SocioRequestMapper socioRequestMapper;
    private final SocioResponseMapper socioResponseMapper;

    public SocioController(SocioService socioService,
                           SocioRequestMapper socioRequestMapper,
                           SocioResponseMapper socioResponseMapper) {
        this.socioService = socioService;
        this.socioRequestMapper = socioRequestMapper;
        this.socioResponseMapper = socioResponseMapper;
    }

    @Operation(summary = "Crear un nuevo socio", description = "Crea y registra un nuevo socio en la base de datos")
    @PostMapping
    public ResponseEntity<SocioResponse> createSocio(@Valid @RequestBody SocioRequest socioRequest) {
        Socio socio = socioRequestMapper.dtoToEntity(socioRequest);
        Socio createdSocio = socioService.createSocio(socio);
        SocioResponse socioResponse = socioResponseMapper.entityToResponse(createdSocio);
        return new ResponseEntity<>(socioResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener un socio por ID", description = "Devuelve un socio específico por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<SocioResponse> getSocioById(@PathVariable Integer id) {
        Socio socio = socioService.getSocioById(id);
        SocioResponse socioResponse = socioResponseMapper.entityToResponse(socio);
        return ResponseEntity.ok(socioResponse);
    }

    @Operation(summary = "Actualizar un socio", description = "Actualiza los datos de un socio existente")
    @PutMapping("/{id}")
    public ResponseEntity<SocioResponse> updateSocio(@PathVariable Integer id, @Valid @RequestBody SocioRequest personDetails) {
        Socio socio = socioRequestMapper.dtoToEntity(personDetails);
        Socio updatedSocio = socioService.updateSocio(id, socio);
        SocioResponse socioResponse = socioResponseMapper.entityToResponse(updatedSocio);
        return ResponseEntity.ok(socioResponse);
    }

    @Operation(summary = "Eliminar un socio por ID", description = "Elimina un socio de la base de datos usando su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSocio(@PathVariable Integer id) {
        socioService.deleteSocioById(id);
        return ResponseEntity.noContent().build();
    }

    // Este método no está implementado completamente.
    // Debería devolver una respuesta relacionada con Barco, no con Socio.
    @Operation(summary = "Añadir un barco a un socio", description = "Asocia un barco nuevo o existente a un socio")
    @PostMapping("/{socioId}/barcos")
    public ResponseEntity<?> addBarcoToSocio(@PathVariable Integer socioId, @RequestBody Barco barco) {
        // Implementación incompleta, necesitarías añadir el mapeador correspondiente para Barco.
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }


}
