package com.sergio.apirest.patron;

import com.sergio.apirest.patron.dto.PatronRequest;
import com.sergio.apirest.patron.dto.PatronRequestMapper;
import com.sergio.apirest.patron.dto.PatronResponse;
import com.sergio.apirest.patron.dto.PatronResponseMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/patrons")
@Api(value = "PatronController", description = "Controlador para la gestión de patrones")
public class PatronController {
    private final PatronService patronService;
    private final PatronRequestMapper patronRequestMapper;
    private final PatronResponseMapper patronResponseMapper;

    @Autowired
    public PatronController(PatronService patronService, PatronRequestMapper patronRequestMapper, PatronResponseMapper patronResponseMapper) {
        this.patronService = patronService;
        this.patronRequestMapper = patronRequestMapper;
        this.patronResponseMapper = patronResponseMapper;
    }

    @GetMapping
    @ApiOperation(value = "Obtiene todos los patrones", notes = "Retorna una lista de todos los patrones disponibles")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Patrones encontrados exitosamente")
    })
    public List<PatronResponse> getAllPatrons() {
        return patronService.findAllPatrones().stream()
                .map(patronResponseMapper::entityToResponse)
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    @ApiOperation(value = "Busca un patrón por ID", notes = "Retorna un patrón específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Patrón encontrado"),
            @ApiResponse(code = 404, message = "Patrón no encontrado")
    })
    public ResponseEntity<PatronResponse> getPatronById(@PathVariable Integer id) {
        return patronService.findPatronById(id)
                .map(patron -> ResponseEntity.ok(patronResponseMapper.entityToResponse(patron)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ApiOperation(value = "Crea un nuevo patrón", notes = "Guarda y retorna el patrón creado")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Patrón creado exitosamente"),
    })
    public ResponseEntity<PatronResponse> createPatron(@RequestBody PatronRequest patronRequest) {
        Patron patron = patronRequestMapper.dtoToEntity(patronRequest);
        Patron savedPatron = patronService.savePatron(patron);
        return new ResponseEntity<>(patronResponseMapper.entityToResponse(savedPatron), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Actualiza un patrón existente", notes = "Actualiza y retorna el patrón especificado por el ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Patrón actualizado exitosamente"),
            @ApiResponse(code = 404, message = "Patrón no encontrado")
    })
    public ResponseEntity<PatronResponse> updatePatron(@PathVariable Integer id, @RequestBody PatronRequest patronRequest) {
        try {
            Patron updatedPatron = patronService.updatePatron(id, patronRequestMapper.dtoToEntity(patronRequest));
            return ResponseEntity.ok(patronResponseMapper.entityToResponse(updatedPatron));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Elimina un patrón por su ID", notes = "Elimina el patrón especificado por el ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Patrón eliminado exitosamente"),
            @ApiResponse(code = 404, message = "Patrón no encontrado")
    })
    public ResponseEntity<Void> deletePatron(@PathVariable Integer id) {
        boolean isDeleted = patronService.deletePatron(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
