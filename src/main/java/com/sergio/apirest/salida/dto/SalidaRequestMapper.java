package com.sergio.apirest.salida.dto;


import com.sergio.apirest.salida.Salida;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface SalidaRequestMapper {

    Salida dtoToEntity(SalidaRequest salidaRequest);
}
