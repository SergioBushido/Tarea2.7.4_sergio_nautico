package com.sergio.apirest.socio.dto;

import com.sergio.apirest.socio.Socio;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SocioResponseMapper {
    SocioResponse entityToResponse(Socio socio);
    List<SocioResponse> entitiesToResponses(List<Socio> socios);
}
