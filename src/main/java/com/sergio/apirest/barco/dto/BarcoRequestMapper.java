package com.sergio.apirest.barco.dto;


import com.sergio.apirest.barco.Barco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BarcoRequestMapper {

    Barco dtoToEntity(BarcoRequest barcoRequest);
}
