package com.campanha_insentivo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.campanha_insentivo.dtos.ParticipanteDTO;
import com.campanha_insentivo.model.Participante;

@Mapper(componentModel = "string")
public interface ParticipanteMapper {
    ParticipanteMapper INSTANCE = Mappers.getMapper(ParticipanteMapper.class);
    Participante toEntity(ParticipanteDTO dto);
    ParticipanteDTO tDto(Participante entity);

}
