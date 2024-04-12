package com.sergio.apirest.socio.dto;

import com.sergio.apirest.socio.Socio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SocioRequestMapper {
    Socio dtoToEntity(SocioRequest socioRequest);
    void updateEntityFromDto(SocioRequest socioRequest, Socio entity);
}
