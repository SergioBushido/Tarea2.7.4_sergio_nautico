package com.sergio.apirest.barco.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BarcoRequest {
    private String matricula;
    private String nombre;
    private Integer numeroAmarre;
    private Double cuotaAmarre;
    private Integer socioId;
}
