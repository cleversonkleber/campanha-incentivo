package com.campanha_insentivo.services;

import java.util.List;
import java.util.stream.Collectors;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campanha_insentivo.dtos.CampanhaDto;
import com.campanha_insentivo.mapper.CampanhaMapper;
import com.campanha_insentivo.model.Campanha;
import com.campanha_insentivo.repositories.CampanhaRepository;

@Service
public class CampanhaService {

    private Logger logger = Logger.getLogger(CampanhaService.class.getName());

    @Autowired
    private CampanhaRepository repository;

    private CampanhaMapper mapper;
    

    public CampanhaService(CampanhaMapper mapper) {
        this.mapper = mapper;
    }


    public CampanhaDto criarCampanha(CampanhaDto dto) {
        logger.info("Criando campanha!");
        Campanha campanha = repository.save(mapper.toEntity(dto));
        return mapper.tDto(campanha);
    }

    public List<CampanhaDto> findAll() {
        logger.info("Listar todas as campanhas!");
        return repository.findAll()
                .stream()
                .map(mapper::tDto)
                .collect(Collectors.toList());
    }


}
