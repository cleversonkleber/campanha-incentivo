package com.campanha_insentivo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campanha_insentivo.model.Campanha;
import com.campanha_insentivo.repositories.CampanhaRepository;

@Service
public class CampanhaService {

    @Autowired
    private CampanhaRepository repository;

    public Campanha criarCampanha(Campanha campanha) {
        repository.save(campanha);
        return campanha;
    }

    public List<Campanha> findAll() {
        return repository.findAll();
    }


}
