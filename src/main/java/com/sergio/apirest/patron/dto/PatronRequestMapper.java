package com.sergio.apirest.patron.dto;

import com.sergio.apirest.patron.Patron;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PatronRequestMapper {
    Patron dtoToEntity(PatronRequest patronRequest);
}
