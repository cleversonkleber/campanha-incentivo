package com.campanha_incentivo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.campanha_incentivo.dtos.EventoDto;
import com.campanha_incentivo.entities.Evento;

@Mapper(componentModel = "spring")
public interface EventoMapper {
    EventoMapper INSTANCE = Mappers.getMapper(EventoMapper.class);
    Evento toEntity(EventoDto dto);
    
    @Mapping(target = "cpf_participante", 
        source = "entity.participante.cpf_participante")
    @Mapping(target = "nomeCampanha", 
        source = "entity.campanha.nomeCampanha")
    EventoDto toDto(Evento entity);

}