package com.sergio.apirest.socio.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SocioResponse {
    private Integer id;
    private String nombre;
    private String apellidos;
}
