package com.sergio.apirest.socio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SocioResponse {
    private Integer id;
    private String nombre;
    private String apellidos;
}
