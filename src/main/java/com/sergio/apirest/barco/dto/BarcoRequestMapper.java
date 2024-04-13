package com.sergio.apirest.barco.dto;


import com.sergio.apirest.barco.Barco;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BarcoRequestMapper {

    Barco dtoToEntity(BarcoRequest barcoRequest);
    void updateEntityFromDto(BarcoRequest dto, @MappingTarget Barco entity);

}
