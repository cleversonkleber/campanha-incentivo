package com.campanha_insentivo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campanha_insentivo.dtos.CampanhaDto;
import com.campanha_insentivo.mapper.CampanhaMapper;
import com.campanha_insentivo.model.Campanha;
import com.campanha_insentivo.repositories.CampanhaRepository;

@Service
public class CampanhaService {

    @Autowired
    private CampanhaRepository repository;

    private CampanhaMapper mapper;
    

    public CampanhaService(CampanhaMapper mapper) {
        this.mapper = mapper;
    }


    public CampanhaDto criarCampanha(CampanhaDto dto) {
        Campanha campanha = repository.save(mapper.toEntity(dto));
        return mapper.tDto(campanha);
    }

    public List<CampanhaDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::tDto)
                .collect(Collectors.toList());
    }


}
