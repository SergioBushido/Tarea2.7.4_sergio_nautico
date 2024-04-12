package com.sergio.apirest.salida.salidaDto;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalidaDto {
    private Integer id;
    private LocalDateTime fechaHoraSalida;
    private String destino;
    private Integer barcoId;
    private Integer patronId;

}