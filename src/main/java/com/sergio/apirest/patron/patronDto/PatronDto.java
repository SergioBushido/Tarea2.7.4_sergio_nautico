package com.sergio.apirest.patron.patronDto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatronDto {
    private Integer id;
    private String nombre;
    private String licencia;
}
