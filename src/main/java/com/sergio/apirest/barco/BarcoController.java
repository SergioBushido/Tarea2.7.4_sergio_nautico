package com.sergio.apirest.barco;

import com.sergio.apirest.barco.dto.BarcoRequest;
import com.sergio.apirest.barco.dto.BarcoResponse;
import com.sergio.apirest.barco.dto.BarcoRequestMapper;
import com.sergio.apirest.barco.dto.BarcoResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/barcos")
public class BarcoController {

    private final BarcoService barcoService;
    private final BarcoRequestMapper barcoRequestMapper;
    private final BarcoResponseMapper barcoResponseMapper;

    public BarcoController(BarcoService barcoService,
                           BarcoRequestMapper barcoRequestMapper,
                           BarcoResponseMapper barcoResponseMapper) {
        this.barcoService = barcoService;
        this.barcoRequestMapper = barcoRequestMapper;
        this.barcoResponseMapper = barcoResponseMapper;
    }

    @Operation(summary = "Obtener todos los barcos", description = "Retorna todos los barcos disponibles")
    @ApiResponse(responseCode = "200", description = "Operación exitosa",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BarcoResponse.class)))
    @GetMapping
    public ResponseEntity<List<BarcoResponse>> getAllBarcos() {
        List<Barco> barcos = barcoService.findAll();
        List<BarcoResponse> barcoResponses = barcos.stream()
                .map(barcoResponseMapper::entityToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(barcoResponses);
    }

    @Operation(summary = "Obtener un barco por ID", description = "Retorna un barco específico por su ID")
    @ApiResponse(responseCode = "200", description = "Barco encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BarcoResponse.class)))
    @ApiResponse(responseCode = "404", description = "Barco no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<BarcoResponse> getBarcoById(@Parameter(description = "ID del barco a buscar")
                                                      @PathVariable Integer id) {
        return barcoService.findById(id)
                .map(barco -> ResponseEntity.ok(barcoResponseMapper.entityToResponse(barco)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo barco", description = "Inserta un nuevo barco en la base de datos")
    @ApiResponse(responseCode = "201", description = "Barco creado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BarcoResponse.class)))
    @PostMapping
    public ResponseEntity<BarcoResponse> createBarco(@Parameter(description = "Datos del nuevo barco")
                                                     @RequestBody BarcoRequest barcoRequest) {
        Barco barco = barcoRequestMapper.dtoToEntity(barcoRequest);
        Barco newBarco = barcoService.save(barco);
        BarcoResponse barcoResponse = barcoResponseMapper.entityToResponse(newBarco);
        return ResponseEntity.status(HttpStatus.CREATED).body(barcoResponse);
    }

    @Operation(summary = "Actualizar un barco", description = "Actualiza los datos de un barco existente")
    @ApiResponse(responseCode = "200", description = "Barco actualizado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BarcoResponse.class)))
    @ApiResponse(responseCode = "404", description = "Barco no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<BarcoResponse> updateBarco(@Parameter(description = "ID del barco a actualizar")
                                                     @PathVariable Integer id,
                                                     @Parameter(description = "Datos actualizados del barco")
                                                     @RequestBody BarcoRequest barcoRequest) {
        return barcoService.findById(id).map(existingBarco -> {
            barcoRequestMapper.updateEntityFromDto(barcoRequest, existingBarco);
            Barco updatedBarco = barcoService.save(existingBarco);
            return ResponseEntity.ok(barcoResponseMapper.entityToResponse(updatedBarco));
        }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un barco", description = "Elimina un barco de la base de datos por su ID")
    @ApiResponse(responseCode = "200", description = "Barco eliminado")
    @ApiResponse(responseCode = "404", description = "Barco no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBarco(@Parameter(description = "ID del barco a eliminar")
                                            @PathVariable Integer id) {
        return barcoService.findById(id).map(barco -> {
            barcoService.deleteById(barco.getId());
            return ResponseEntity.ok().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }

    // ...

    @Operation(summary = "Buscar barcos por nombre", description = "Retorna una lista de barcos que coinciden con el nombre especificado")
    @ApiResponse(responseCode = "200", description = "Búsqueda exitosa",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BarcoResponse.class)))
    @GetMapping("/buscar")
    public ResponseEntity<List<BarcoResponse>> getBarcosPorNombre(@Parameter(description = "Nombre del barco a buscar")
                                                                  @RequestParam String nombre) {
        List<Barco> barcos = barcoService.findByNombre(nombre);
        List<BarcoResponse> barcoResponses = barcos.stream()
                .map(barcoResponseMapper::entityToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(barcoResponses);
    }

    @Operation(summary = "Obtener la cuota total de amarre", description = "Calcula la cuota total de amarre para todos los barcos")
    @ApiResponse(responseCode = "200", description = "Cuota total calculada",
            content = @Content(mediaType = "application/json"))
    @GetMapping("/cuotaTotalAmarre")
    public ResponseEntity<Double> getTotalCuotaAmarre() {
        Double totalCuotaAmarre = barcoService.getTotalCuotaAmarre();
        return ResponseEntity.ok(totalCuotaAmarre);
    }

    @Operation(summary = "Actualizar la cuota de amarre de un barco", description = "Actualiza la cuota de amarre de un barco por su número de amarre")
    @ApiResponse(responseCode = "200", description = "Cuota de amarre actualizada",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BarcoResponse.class)))
    @ApiResponse(responseCode = "404", description = "Barco no encontrado")
    @PutMapping("/actualizarCuotaAmarre")
    public ResponseEntity<BarcoResponse> updateCuotaAmarre(@Parameter(description = "Número de amarre del barco")
                                                           @RequestParam Integer numeroAmarre,
                                                           @Parameter(description = "Nueva cuota de amarre a establecer")
                                                           @RequestParam Double nuevaCuota) {
        return barcoService.updateCuotaAmarreByNumeroAmarre(numeroAmarre, nuevaCuota)
                .map(barco -> ResponseEntity.ok(barcoResponseMapper.entityToResponse(barco)))
                .orElse(ResponseEntity.notFound().build());
    }

// ...

}
