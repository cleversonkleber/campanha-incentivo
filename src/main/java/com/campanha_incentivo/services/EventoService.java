package com.campanha_incentivo.services;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.campanha_incentivo.dtos.EventoDto;
import com.campanha_incentivo.entities.Campanha;
import com.campanha_incentivo.entities.Evento;
import com.campanha_incentivo.entities.Participante;
import com.campanha_incentivo.mapper.EventoMapper;
import com.campanha_incentivo.repositories.CampanhaRepository;
import com.campanha_incentivo.repositories.EventoRepository;
import com.campanha_incentivo.repositories.ParticipanteRepository;

@Service
public class EventoService implements IService<EventoDto, Long>{

    private Logger logger = Logger.getLogger(EventoService.class.getName());

  
    @Autowired
    private CampanhaRepository campanhaRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ParticipanteRepository participanteRepository;

    @Autowired
    EventoMapper eventoMapper;

    

    @Override
    public EventoDto criar(EventoDto dto) {

        Participante participante = participanteRepository.findByCpf(dto.cpf_participante())
                .orElseThrow(
                    ()-> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Participante com o CPF"+dto.cpf_participante() + " não encontrado!"
                ));

        Campanha campanha = campanhaRepository.findByNome(dto.nomeCampanha().toString())
                .orElseThrow(()-> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Campanha com o nome"+dto.nomeCampanha() + " não encontrado!"
                ));

        
        Evento evento = eventoMapper.toEntity(dto);
        evento.setCampanha(campanha);
        evento.setParticipante(participante);

        Evento eventoSalvo = eventoRepository.save(evento);
        
        return eventoMapper.toDto(eventoSalvo);
    }

    @Override
    public List<EventoDto> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public EventoDto update(EventoDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

   

   

    


}
