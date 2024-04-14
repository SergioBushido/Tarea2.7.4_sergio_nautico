package com.sergio.apirest.patron;

import com.sergio.apirest.barco.dto.BarcoResponse;
import com.sergio.apirest.patron.dto.PatronRequest;
import com.sergio.apirest.patron.dto.PatronRequestMapper;
import com.sergio.apirest.patron.dto.PatronResponse;
import com.sergio.apirest.patron.dto.PatronResponseMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class PatronController {

    private final PatronService patronService;


    @GetMapping
    @ApiOperation(value = "Obtiene todos los patrones", notes = "Retorna una lista de todos los patrones disponibles")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Patrones encontrados exitosamente")
    })
    public List<PatronResponse> getAllPatrons() {
        return patronService.findAllPatrones();
    }
    @GetMapping("/{id}")
    @ApiOperation(value = "Busca un patrón por ID", notes = "Retorna un patrón específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Patrón encontrado"),
            @ApiResponse(code = 404, message = "Patrón no encontrado")
    })
    public ResponseEntity<PatronResponse> getPatronById(@PathVariable final Integer id) {
        return patronService.findPatronById(id)
                .map(patron -> ResponseEntity.ok().body(patron))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ApiOperation(value = "Crea un nuevo patrón", notes = "Guarda y retorna el patrón creado")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Patrón creado exitosamente"),
    })
    public ResponseEntity<PatronResponse> createPatron(@RequestBody final PatronRequest patronRequest) {
        final PatronResponse newPatron = patronService.savePatron(patronRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPatron);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Actualiza un patrón existente", notes = "Actualiza y retorna el patrón especificado por el ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Patrón actualizado exitosamente"),
            @ApiResponse(code = 404, message = "Patrón no encontrado")
    })
    public ResponseEntity<PatronResponse> updatePatron(@PathVariable final Integer id, @RequestBody final PatronRequest patronRequest) {
        return patronService.updatePatron(id, patronRequest)
                .map(patron -> ResponseEntity.ok().body(patron))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Elimina un patrón por su ID", notes = "Elimina el patrón especificado por el ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Patrón eliminado exitosamente"),
            @ApiResponse(code = 404, message = "Patrón no encontrado")
    })
    public ResponseEntity<Void> deletePatron(@PathVariable final Integer id) {
        boolean isDeleted = patronService.deletePatron(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
