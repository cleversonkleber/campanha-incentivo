package com.campanha_insentivo.services;

import java.nio.file.OpenOption;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campanha_insentivo.dtos.ParticipanteDTO;
import com.campanha_insentivo.exception.handler.ResourceExistsException;
import com.campanha_insentivo.mapper.ParticipanteMapper;
import com.campanha_insentivo.model.Participante;
import com.campanha_insentivo.repositories.ParticipanteRepository;

@Service
public class ParticipanteService {

    @Autowired
    private ParticipanteRepository repository;

    private ParticipanteMapper mapper;

    public ParticipanteService(ParticipanteMapper mapper){
        this.mapper = mapper;
    }

    public ParticipanteDTO criarParticipante(ParticipanteDTO dto) {
        Optional<Participante> optional = repository.findByCpf(dto.cpf());
        if (optional.isPresent()) {
            throw new ResourceExistsException(
                "Existe um participante com o cpf: "+ dto.cpf()
            );
        }
        Participante participante = repository.save(mapper.toEntity(dto));
        return mapper.tDto(participante);
    }

    public List<ParticipanteDTO> findAll() {
        return repository.findAll()
               .stream()
               .map(mapper::tDto)
               .collect(Collectors.toList());
    }
}
