package com.campanha_insentivo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.campanha_insentivo.dtos.ParticipanteDTO;
import com.campanha_insentivo.model.Participante;

@Mapper(componentModel = "spring")
public interface ParticipanteMapper {
    ParticipanteMapper INSTANCE = Mappers.getMapper(ParticipanteMapper.class);
    Participante toEntity(ParticipanteDTO dto);
    
    @Mapping(target = "nome_completo", source = "nome_completo")
    @Mapping(target = "cpf", source = "cpf")
    @Mapping(target = "email", source = "email")
    ParticipanteDTO tDto(Participante entity);

}
