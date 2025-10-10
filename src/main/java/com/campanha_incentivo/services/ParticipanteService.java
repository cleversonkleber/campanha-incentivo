package com.campanha_incentivo.services;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.campanha_incentivo.dtos.ParticipanteDTO;
import com.campanha_incentivo.exception.handler.ResourceExistsException;
import com.campanha_incentivo.mapper.ParticipanteMapper;
import com.campanha_incentivo.model.Participante;
import com.campanha_incentivo.repositories.ParticipanteRepository;

@Service
public class ParticipanteService {

    private Logger logger = Logger.getLogger(CampanhaService.class.getName());

    
    private ParticipanteRepository repository;

    private ParticipanteMapper mapper;

    public ParticipanteService(ParticipanteRepository repository, ParticipanteMapper mapper){
        this.mapper = mapper;
        this.repository = repository;
    }

    public ParticipanteDTO criarParticipante(ParticipanteDTO dto) {
        
        Optional<Participante> optional = repository.findByCpf(dto.cpf());
        if (optional.isPresent()) {
            logger.warning("Existe um participante com o cpf: "+ dto.cpf());
            throw new ResourceExistsException(
                "Existe um participante com o cpf: "+ dto.cpf()
            );
        
        }
        logger.info("Criando participante!");
        Participante participante = repository.save(mapper.toEntity(dto));
        return mapper.tDto(participante);
    }

    public List<ParticipanteDTO> findAll() {
         logger.info("Listando todos os participantes.");
        return repository.findAll()
               .stream()
               .map(mapper::tDto)
               .collect(Collectors.toList());
    }
}
