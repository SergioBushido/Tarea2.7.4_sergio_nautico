package com.sergio.apirest.salida.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class SalidaResponse {
    private Integer id;
    private LocalDateTime fechaHoraSalida;
    private String destino;
    private Integer barcoId;
    private Integer patronId;
}
