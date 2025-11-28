package com.campanha_incentivo.services;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.campanha_incentivo.dtos.request.EventoDto;
import com.campanha_incentivo.entities.CampanhaEntity;
import com.campanha_incentivo.entities.EventoEntity;
import com.campanha_incentivo.entities.UsuarioEntity;
import com.campanha_incentivo.exception.handler.ResourceNotFoundException;
import com.campanha_incentivo.mapper.EventoMapper;
import com.campanha_incentivo.repositories.CampanhaRepository;
import com.campanha_incentivo.repositories.EventoRepository;
import com.campanha_incentivo.repositories.GrupoOrganizacionalRepository;
import com.campanha_incentivo.repositories.UsuarioRepository;

@Service
public class EventoService implements IService<EventoDto, Long>{

    
	private UsuarioRepository usuarioRepository;
    private CampanhaRepository campanhaRepository;
    private EventoRepository eventoRepository;
    private GrupoOrganizacionalRepository grupoOrganizacionalRepository;
    private EventoMapper eventoMapper;
    private Logger logger = Logger.getLogger(EventoService.class.getName());
    

    public EventoService(CampanhaRepository campanhaRepository, EventoRepository eventoRepository,
			GrupoOrganizacionalRepository grupoOrganizacionalRepository, EventoMapper eventoMapper) {
		super();
		this.campanhaRepository = campanhaRepository;
		this.eventoRepository = eventoRepository;
		this.grupoOrganizacionalRepository = grupoOrganizacionalRepository;
		this.eventoMapper = eventoMapper;
	}

	@Override
    public EventoDto criar(EventoDto dto) {
		String nomeCampanhaNormalizado = dto.nomeCampanha().toUpperCase().toString();
		
        UsuarioEntity usuarioEntity = usuarioRepository.findByCpf(dto.cpf_usuario())
                .orElseThrow(
                    ()-> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Usuário com o CPF"+dto.cpf_usuario() + " não encontrado!"
                ));

        CampanhaEntity campanha = campanhaRepository.findByNome(nomeCampanhaNormalizado)
                .orElseThrow(()-> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Campanha com o nome"+dto.nomeCampanha() + " não encontrado!"
                ));

        
        EventoEntity evento = eventoMapper.toEntity(dto);
        evento.setCampanha(campanha);
        evento.setUsuarioEntity(usuarioEntity);
        

        EventoEntity eventoSalvo = eventoRepository.save(evento);
        
        return eventoMapper.toDto(eventoSalvo);
    }

    @Override
    public List<EventoDto> findAll() {
    	logger.info("Listar todas os eventos.");
    	return eventoRepository.findAll()
    			.stream()
    			.map(eventoMapper::toDto)
    			.collect(Collectors.toList());
    			

    }

    @Override
    public void delete(Long id) {
    	var evento = eventoRepository.findById(id).orElseThrow(
    			()-> new ResourceNotFoundException("Id não encontrado"));
    	eventoRepository.delete(evento);
    }

    @Override
    public EventoDto update(EventoDto dto) {
    	   UsuarioEntity usuarioEntity = usuarioRepository.findByCpf(dto.cpf_usuario())
                   .orElseThrow(
                       ()-> new ResponseStatusException(
                       HttpStatus.NOT_FOUND,
                       "Participante com o CPF"+dto.cpf_usuario() + " não encontrado!"
                   ));

           CampanhaEntity campanha = campanhaRepository.findByNome(dto.nomeCampanha().toString())
                   .orElseThrow(()-> new ResponseStatusException(
                       HttpStatus.NOT_FOUND,
                       "Campanha com o nome"+dto.nomeCampanha() + " não encontrado!"
                   ));
           
           EventoEntity eventoBd= eventoRepository.findById(dto.id_envento())
        		   .orElseThrow(
                           ()-> new ResponseStatusException(
                           HttpStatus.NOT_FOUND,
                           "ID evento não encontrado"+dto.id_envento() + " não encontrado!"
                       ));
           
           eventoBd.setCampanha(campanha);
           eventoBd.setUsuarioEntity(usuarioEntity);
           eventoBd.setDataHoraOcorrencia(dto.dataHoraOcorrencia());
           eventoBd.setDescricao(dto.descricao());
           eventoBd.setTipoEvento(dto.tipoEvento());
           eventoBd.setPontosGerados(dto.pontosGerados());
           eventoBd.setValor(dto.valor());
           eventoRepository.save(eventoBd);
           return eventoMapper.toDto(eventoBd);
    }

   

   

    


}
