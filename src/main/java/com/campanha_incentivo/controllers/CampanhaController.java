package com.campanha_incentivo.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campanha_incentivo.dtos.CampanhaDto;
import com.campanha_incentivo.services.CampanhaService;

@RestController
@RequestMapping("/campanha")
public class CampanhaController {
    
    @Autowired
    private CampanhaService campanhaService;
    private Logger logger = Logger.getLogger(CampanhaController.class.getName());

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CampanhaDto> criarCampanha(@RequestBody CampanhaDto dto){
         logger.info("Criar Campanha!");
        var campanhaDto = campanhaService.criarCampanha(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(campanhaDto);
    }

    @GetMapping( 
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<CampanhaDto> findAll() throws Exception{
        logger.info("Listar todas as Campanhas!");
        return campanhaService.findAll();
    }
    @DeleteMapping(
        value = "{id}"
    )
    public ResponseEntity<?> delete(@PathVariable Long id){
        logger.info("Deletar a Campanha!");
        campanhaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CampanhaDto> update(@RequestBody CampanhaDto dto){
        logger.info("Atualizar camapanha!");
        try{
            return ResponseEntity.ok(campanhaService.update(dto));
        }catch(Exception e){
            logger.info("Erro ao atualizar camapnha! "+e);
            return ResponseEntity.notFound().<CampanhaDto>build();
        }
    }
}
