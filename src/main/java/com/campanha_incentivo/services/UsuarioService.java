package com.campanha_incentivo.services;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.campanha_incentivo.dtos.UsuarioDTO;
import com.campanha_incentivo.entities.UsuarioEntity;
import com.campanha_incentivo.exception.handler.ResourceExistsException;
import com.campanha_incentivo.exception.handler.ResourceNotFoundException;
import com.campanha_incentivo.mapper.UsuarioMapper;
import com.campanha_incentivo.repositories.UsuarioRepository;




@Service
public class UsuarioService {

    private Logger logger = Logger.getLogger(UsuarioService.class.getName());

    private UsuarioRepository usuarioRepository;
    
    private UsuarioMapper mapper;

    
	public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper mapper) {
		this.usuarioRepository = usuarioRepository;
		this.mapper = mapper;
	}


	public UsuarioDTO criarUsuario(UsuarioDTO dto) {
	        
	        Optional<UsuarioEntity> optional = usuarioRepository.findById(dto.id());
	        if (optional.isPresent()) {
	            logger.warning("Existe um participante com o cpf: "+ dto.cpf());
	            throw new ResourceExistsException(
	                "Existe um participante com o cpf: "+ dto.cpf()
	            );
	        
	        }
	        logger.info("Criando usuário!");
	        UsuarioEntity usuario = usuarioRepository.save(mapper.toEntity(dto));
	        return mapper.toDto(usuario);
	    }
	
	public List<UsuarioDTO> findAll() {
	        logger.info("Listando todos os participantes.");
	       return usuarioRepository.findAll()
	              .stream()
	              .map(mapper::toDto)
	              .collect(Collectors.toList());
	   }   
	
    public void delete(Long id) {
        logger.info("Deletar participante!");

        var participante = usuarioRepository.findById(id)
                            .orElseThrow(()-> new ResourceNotFoundException("Id não encontrado"));
        usuarioRepository.delete(participante);
    }
    
    public UsuarioDTO update(UsuarioDTO dto) {
        logger.info("Atualizar participante!");
        Optional<UsuarioEntity> optional = usuarioRepository.findById(dto.id());
         if (!optional.isPresent()) {
            logger.info("Participante não existe com o cpf: "+ dto.cpf());
        }
        UsuarioEntity usuario = optional
                        .orElseThrow(() -> new ResourceNotFoundException("Não existe um participante com o cpf: "+ dto.cpf()));
        usuario.setSenha(dto.senha());
        
        
        return mapper.toDto(usuario);

    }

    public UsuarioDTO findByCpf(UsuarioDTO dto){
        Optional<UsuarioEntity> optional = usuarioRepository.findByCpf(dto.cpf());
        UsuarioEntity usuario = optional
                        .orElseThrow(() -> new ResourceNotFoundException("Não existe um participante com o cpf: "+ dto.cpf()));
        return mapper.toDto(usuario);
    }
    

}
















