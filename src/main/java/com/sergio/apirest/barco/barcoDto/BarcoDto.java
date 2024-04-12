package com.sergio.apirest.barco.barcoDto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BarcoDto {
    private Integer id;
    private String matricula;
    private String nombre;
    private Integer numeroAmarre;
    private Double cuotaAmarre;
    private Integer socioId;
}
