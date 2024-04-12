package com.sergio.apirest.patron.dto;

import com.sergio.apirest.patron.Patron;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatronResponseMapper {
    PatronResponse entityToResponse(Patron patron);
    List<PatronResponse> entitiesToResponses(List<Patron> patrons);
    void updateEntityFromDto(PatronRequest patronRequest, @MappingTarget Patron entity);
}
