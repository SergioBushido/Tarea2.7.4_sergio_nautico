package com.sergio.apirest.salida.dto;

import com.sergio.apirest.salida.Salida;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SalidaResponseMapper {

    SalidaResponse entityToResponse(Salida salida);

    List<SalidaResponse> entitiesToResponses(List<Salida> salidas);


}
