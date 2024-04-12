package com.sergio.apirest.salida.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalidaRequest {
    private LocalDateTime fechaHoraSalida;
    private String destino;
    private Integer barcoId;
    private Integer patronId;
}
