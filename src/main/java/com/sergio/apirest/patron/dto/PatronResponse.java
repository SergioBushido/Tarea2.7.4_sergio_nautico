package com.sergio.apirest.patron.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PatronResponse {
    private Integer id;
    private String nombre;
    private String licencia;
}
