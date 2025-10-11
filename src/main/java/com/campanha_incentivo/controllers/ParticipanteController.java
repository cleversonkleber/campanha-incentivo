package com.campanha_incentivo.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    private Logger logger = Logger.getLogger(ParticipanteController.class.getName());

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ParticipanteDTO> criarParticipante(@RequestBody ParticipanteDTO dto){
        logger.info("Criar participante!");
        ParticipanteDTO participanteDTO = participanteService.criarParticipante(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(participanteDTO);
    }

    @RequestMapping(
        method = RequestMethod.GET, 
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<ParticipanteDTO> findAll() throws Exception{
        logger.info("Listar todos os participantes!");

        return participanteService.findAll();
    }

    @DeleteMapping(
        value = "{id}"
    )
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
         logger.info("Deletar participante!");
        participanteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ParticipanteDTO> update(@RequestBody ParticipanteDTO dto){
        logger.info("Atualizar participante!");
        try{
            return ResponseEntity.ok(participanteService.update(dto));
        }catch(Exception e){
            logger.info("Erro ao atualizar participante! "+e);
            return ResponseEntity.notFound().<ParticipanteDTO>build();
        }
    }
    
}
