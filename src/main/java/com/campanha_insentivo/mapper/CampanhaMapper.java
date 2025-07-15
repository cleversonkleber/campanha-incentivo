package com.campanha_insentivo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.campanha_insentivo.dtos.CampanhaDto;
import com.campanha_insentivo.model.Campanha;


@Mapper(componentModel = "spring")
public interface CampanhaMapper {
    CampanhaMapper INSTANCE = Mappers.getMapper(CampanhaMapper.class);
    Campanha toEntity(CampanhaDto dto);
    CampanhaDto tDto(Campanha entity);

}
