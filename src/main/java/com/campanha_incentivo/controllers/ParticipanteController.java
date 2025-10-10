package com.campanha_incentivo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.campanha_incentivo.dtos.ParticipanteDTO;
import com.campanha_incentivo.services.ParticipanteService;

@RestController
@RequestMapping("/participante")
public class ParticipanteController {

    @Autowired
    private ParticipanteService participanteService;

   

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ParticipanteDTO> criarParticipante(@RequestBody ParticipanteDTO dto){
        ParticipanteDTO participanteDTO = participanteService.criarParticipante(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(participanteDTO);
    }

    @RequestMapping(
        method = RequestMethod.GET, 
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<ParticipanteDTO> findAll() throws Exception{

        return participanteService.findAll();
    }
}
