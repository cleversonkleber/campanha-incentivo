package com.campanha_incentivo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.campanha_incentivo.dtos.EventoDto;
import com.campanha_incentivo.entities.Evento;

@Mapper(componentModel = "spring")
public interface EventoMapper {
    EventoMapper INSTANCE = Mappers.getMapper(EventoMapper.class);

    @Mapping(target = "id_envento",source = "dto.id")
    @Mapping(target = "campanha", ignore = true)
    @Mapping(target = "participante", ignore = true)
    Evento toEntity(EventoDto dto);
    
    @Mapping(target = "cpf_participante",source = "entity.participante.cpf")
    @Mapping(target = "nomeCampanha",source = "entity.campanha.nome")
    @Mapping(target = "id",source = "entity.id_envento")
    EventoDto toDto(Evento entity);

}