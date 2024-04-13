package com.sergio.apirest.socio;

import com.sergio.apirest.barco.Barco;
import com.sergio.apirest.socio.dto.SocioRequest;
import com.sergio.apirest.socio.dto.SocioRequestMapper;
import com.sergio.apirest.socio.dto.SocioResponse;
import com.sergio.apirest.socio.dto.SocioResponseMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/socios")
@Api(value = "SocioController", description = "Controlador para gestionar socios del club náutico")
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

    @PostMapping
    @ApiOperation(value = "Crea un nuevo socio", notes = "Registra un nuevo socio en el club")
    @ApiResponse(code = 201, message = "Socio creado exitosamente")
    public ResponseEntity<SocioResponse> createSocio(@RequestBody SocioRequest socioRequest) {
        Socio socio = socioRequestMapper.dtoToEntity(socioRequest);
        Socio createdSocio = socioService.createSocio(socio);
        SocioResponse socioResponse = socioResponseMapper.entityToResponse(createdSocio);
        return new ResponseEntity<>(socioResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un socio por su ID", notes = "Devuelve los datos de un socio específico")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Socio encontrado"),
            @ApiResponse(code = 404, message = "Socio no encontrado")
    })
    public ResponseEntity<SocioResponse> getSocioById(@PathVariable Integer id) {
        Socio socio = socioService.getSocioById(id);
        if (socio != null) {
            SocioResponse socioResponse = socioResponseMapper.entityToResponse(socio);
            return ResponseEntity.ok(socioResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Actualiza los datos de un socio", notes = "Modifica los datos del socio especificado")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Socio actualizado exitosamente"),
            @ApiResponse(code = 404, message = "Socio no encontrado")
    })
    public ResponseEntity<SocioResponse> updateSocio(@PathVariable Integer id, @RequestBody SocioRequest socioRequest) {
        Socio socioDetails = socioRequestMapper.dtoToEntity(socioRequest);
        Socio updatedSocio = socioService.updateSocio(id, socioDetails);
        SocioResponse socioResponse = socioResponseMapper.entityToResponse(updatedSocio);
        return ResponseEntity.ok(socioResponse);
    }
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Elimina un socio", notes = "Elimina los datos del socio especificado del club")
    @ApiResponse(code = 200, message = "Socio eliminado exitosamente")
    public ResponseEntity<Void> deleteSocioById(@PathVariable Integer id) {
        try {
            socioService.deleteSocioById(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/{socioId}/barcos")
    @ApiOperation(value = "Añade un barco a un socio", notes = "Asocia un nuevo barco al socio especificado")
    @ApiResponse(code = 201, message = "Barco añadido al socio exitosamente")
    public ResponseEntity<Barco> addBarcoToSocio(@PathVariable Integer socioId, @RequestBody Barco newBarco) {
        Barco barco = socioService.addBarcoToSocio(socioId, newBarco);
        return new ResponseEntity<>(barco, HttpStatus.CREATED);
    }


}
