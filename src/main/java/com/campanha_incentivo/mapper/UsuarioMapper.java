package com.campanha_incentivo.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.campanha_incentivo.dtos.request.RegistroUsuarioRequestDto;
import com.campanha_incentivo.entities.UsuarioEntity;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
		UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);
	    UsuarioEntity toEntity(RegistroUsuarioRequestDto dto);
	    @Mapping(target = "id",source = "entity.id_usuario")
	    RegistroUsuarioRequestDto toDto(UsuarioEntity entity);
}
