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

import com.campanha_incentivo.dtos.EventoDto;
import com.campanha_incentivo.services.EventoService;


@RestController
@RequestMapping("/evento")
public class EventoController {
    private Logger logger = Logger.getLogger(ParticipanteController.class.getName());
    
    @Autowired
    private EventoService eventoService;
   



    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<EventoDto> criarParticipante(@RequestBody EventoDto dto){
        logger.info("Criar participante!");
        EventoDto eventoDto = eventoService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventoDto);
    }

    @RequestMapping(
        method = RequestMethod.GET, 
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<EventoDto> findAll() throws Exception{
        logger.info("Listar todos os participantes!");

        return eventoService.findAll();
    }

    @DeleteMapping(
        value = "{id}"
    )
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
         logger.info("Deletar participante!");
        eventoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<EventoDto> update(@RequestBody EventoDto dto){
        logger.info("Atualizar participante!");
        try{
            return ResponseEntity.ok(eventoService.update(dto));
        }catch(Exception e){
            logger.info("Erro ao atualizar participante! "+e);
            return ResponseEntity.notFound().<EventoDto>build();
        }
    }
}
