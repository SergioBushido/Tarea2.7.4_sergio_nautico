package com.sergio.apirest.salida.dto;

import com.sergio.apirest.salida.Salida;
import com.sergio.apirest.barco.BarcoRepository;
import com.sergio.apirest.patron.PatronRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {BarcoRepository.class, PatronRepository.class})
public abstract class SalidaRequestMapper {

    @Autowired
    protected BarcoRepository barcoRepository;
    @Autowired
    protected PatronRepository patronRepository;

    @Mapping(source = "barcoId", target = "barco")
    @Mapping(source = "patronId", target = "patron")
    public abstract Salida dtoToEntity(SalidaRequest request);

    @Mapping(target = "barco", expression = "java(barcoRepository.findById(request.getBarcoId()).orElse(null))")
    @Mapping(target = "patron", expression = "java(patronRepository.findById(request.getPatronId()).orElse(null))")
    public abstract void updateEntityFromDto(SalidaRequest request, @MappingTarget Salida entity);
}
