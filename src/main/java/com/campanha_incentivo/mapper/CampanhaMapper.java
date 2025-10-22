package com.campanha_incentivo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.campanha_incentivo.dtos.CampanhaDto;
import com.campanha_incentivo.entities.CampanhaEntity;


@Mapper(componentModel = "spring")
public interface CampanhaMapper {
    CampanhaMapper INSTANCE = Mappers.getMapper(CampanhaMapper.class);
    CampanhaEntity toEntity(CampanhaDto dto);
    CampanhaDto tDto(CampanhaEntity entity);

}
