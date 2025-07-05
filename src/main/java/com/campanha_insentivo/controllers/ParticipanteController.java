package com.campanha_insentivo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.campanha_insentivo.model.Campanha;
import com.campanha_insentivo.model.Participante;

@RestController
@RequestMapping("/participante")
public class ParticipanteController {

    @Autowired
    private Participante participanteService;

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Campanha criarParticipante(@RequestBody Participante participante){
        return participanteService.criarParticipante(participante);
    }

    @RequestMapping(
        method = RequestMethod.GET, 
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Participante> findAll() throws Exception{
        return participanteService.findAll();
    }
}
