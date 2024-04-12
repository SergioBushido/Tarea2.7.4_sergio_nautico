package com.sergio.apirest.socio.socioDto;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SocioDto {
    private Integer id;
    private String nombre;
    private String apellidos;
    private Set<Integer> barcosIds;

}