package com.campanha_incentivo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.campanha_incentivo.dtos.CampanhaDto;
import com.campanha_incentivo.entities.Campanha;


@Mapper(componentModel = "spring")
public interface CampanhaMapper {
    CampanhaMapper INSTANCE = Mappers.getMapper(CampanhaMapper.class);
    Campanha toEntity(CampanhaDto dto);
    CampanhaDto tDto(Campanha entity);

}
