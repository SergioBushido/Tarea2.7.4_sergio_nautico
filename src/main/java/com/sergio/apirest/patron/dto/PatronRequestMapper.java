package com.sergio.apirest.patron.dto;

import com.sergio.apirest.patron.Patron;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PatronRequestMapper {
    Patron dtoToEntity(PatronRequest patronRequest);
    void updateEntityFromDto(PatronRequest patronRequest, @MappingTarget Patron entity);

}
