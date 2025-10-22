package com.campanha_incentivo.services;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.campanha_incentivo.dtos.EventoDto;
import com.campanha_incentivo.entities.CampanhaEntity;
import com.campanha_incentivo.entities.EventoEntity;
import com.campanha_incentivo.entities.ParticipanteEntity;
import com.campanha_incentivo.exception.handler.ResourceNotFoundException;
import com.campanha_incentivo.mapper.EventoMapper;
import com.campanha_incentivo.repositories.CampanhaRepository;
import com.campanha_incentivo.repositories.EventoRepository;
import com.campanha_incentivo.repositories.ParticipanteRepository;

@Service
public class EventoService implements IService<EventoDto, Long>{

    

    private CampanhaRepository campanhaRepository;
    private EventoRepository eventoRepository;
    private ParticipanteRepository participanteRepository;
    private EventoMapper eventoMapper;
    private Logger logger = Logger.getLogger(EventoService.class.getName());
    

    public EventoService(CampanhaRepository campanhaRepository, EventoRepository eventoRepository,
			ParticipanteRepository participanteRepository, EventoMapper eventoMapper) {
		super();
		this.campanhaRepository = campanhaRepository;
		this.eventoRepository = eventoRepository;
		this.participanteRepository = participanteRepository;
		this.eventoMapper = eventoMapper;
	}

	@Override
    public EventoDto criar(EventoDto dto) {
		String nomeCampanhaNormalizado = dto.nomeCampanha().toUpperCase().toString();
		
        ParticipanteEntity participante = participanteRepository.findByCpf(dto.cpf_participante())
                .orElseThrow(
                    ()-> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Participante com o CPF"+dto.cpf_participante() + " não encontrado!"
                ));

        CampanhaEntity campanha = campanhaRepository.findByNome(nomeCampanhaNormalizado)
                .orElseThrow(()-> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Campanha com o nome"+dto.nomeCampanha() + " não encontrado!"
                ));

        
        EventoEntity evento = eventoMapper.toEntity(dto);
        evento.setCampanha(campanha);
        evento.setParticipante(participante);
        

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
    	   ParticipanteEntity participante = participanteRepository.findByCpf(dto.cpf_participante())
                   .orElseThrow(
                       ()-> new ResponseStatusException(
                       HttpStatus.NOT_FOUND,
                       "Participante com o CPF"+dto.cpf_participante() + " não encontrado!"
                   ));

           CampanhaEntity campanha = campanhaRepository.findByNome(dto.nomeCampanha().toString())
                   .orElseThrow(()-> new ResponseStatusException(
                       HttpStatus.NOT_FOUND,
                       "Campanha com o nome"+dto.nomeCampanha() + " não encontrado!"
                   ));
           
           EventoEntity eventoBd= eventoRepository.findById(dto.id())
        		   .orElseThrow(
                           ()-> new ResponseStatusException(
                           HttpStatus.NOT_FOUND,
                           "Participante com o CPF"+dto.cpf_participante() + " não encontrado!"
                       ));
           
           eventoBd.setCampanha(campanha);
           eventoBd.setParticipante(participante);
           eventoBd.setDataHoraOcorrencia(dto.dataHoraOcorrencia());
           eventoBd.setDescricao(dto.descricao());
           eventoBd.setTipoEvento(dto.tipoEvento());
           eventoBd.setPontosGerados(dto.pontosGerados());
           eventoBd.setValor(dto.valor());
           eventoRepository.save(eventoBd);
           return eventoMapper.toDto(eventoBd);
    }

   

   

    


}
