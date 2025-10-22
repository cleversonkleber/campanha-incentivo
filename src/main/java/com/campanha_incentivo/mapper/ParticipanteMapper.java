package com.campanha_incentivo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.campanha_incentivo.dtos.ParticipanteDTO;
import com.campanha_incentivo.entities.ParticipanteEntity;

@Mapper(componentModel = "spring")
public interface ParticipanteMapper {
    ParticipanteMapper INSTANCE = Mappers.getMapper(ParticipanteMapper.class);
    ParticipanteEntity toEntity(ParticipanteDTO dto);
    
    @Mapping(target = "nome_completo", source = "nome_completo")
    @Mapping(target = "cpf", source = "cpf")
    @Mapping(target = "email", source = "email")
    ParticipanteDTO tDto(ParticipanteEntity entity);


}
