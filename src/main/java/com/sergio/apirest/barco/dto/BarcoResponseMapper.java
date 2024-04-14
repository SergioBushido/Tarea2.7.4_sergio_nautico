package com.sergio.apirest.barco.dto;

import com.sergio.apirest.barco.Barco;
import java.util.List;
import java.util.Optional;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface BarcoResponseMapper {

    BarcoResponse entityToResponse(Barco barco);

    void updateEntityFromDto(BarcoRequest barcoRequest, @MappingTarget Barco entity);

    List<BarcoResponse> entitiesToResponses(List<Barco> barcos);

}