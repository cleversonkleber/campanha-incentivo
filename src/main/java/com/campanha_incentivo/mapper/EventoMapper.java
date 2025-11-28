package com.campanha_incentivo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.campanha_incentivo.dtos.request.EventoDto;
import com.campanha_incentivo.entities.EventoEntity;

@Mapper(componentModel = "spring")
public interface EventoMapper {
    EventoMapper INSTANCE = Mappers.getMapper(EventoMapper.class);

    @Mapping(target = "campanha", ignore = true)
    EventoEntity toEntity(EventoDto dto);
    @Mapping(target = "nomeCampanha",source = "entity.campanha.nome")
    EventoDto toDto(EventoEntity entity);

}