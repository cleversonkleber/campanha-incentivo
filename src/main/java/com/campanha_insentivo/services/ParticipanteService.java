package com.campanha_insentivo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.campanha_insentivo.model.Participante;
import com.campanha_insentivo.repositories.ParticipanteRepository;

@Service
public class ParticipanteService {

    @Autowired
    private ParticipanteRepository repository;

    public Participante criarParticipante(Participante participante) {
        repository.save(participante);
        return participante;
    }

    public List<Participante> findAll() {
        return repository.findAll();
    }
}
