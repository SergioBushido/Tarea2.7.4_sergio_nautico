package com.sergio.apirest.barco.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BarcoResponse {
    private Integer id;
    private String matricula;
    private String nombre;
    private Integer numeroAmarre;
    private Double cuotaAmarre;
    private Integer socioId;
}
