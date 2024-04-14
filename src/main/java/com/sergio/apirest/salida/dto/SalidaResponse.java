package com.sergio.apirest.salida.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalidaResponse {
    private Integer id;
    private LocalDateTime fechaHoraSalida;
    private String destino;
    private Integer barcoId;
    private Integer patronId;
}
