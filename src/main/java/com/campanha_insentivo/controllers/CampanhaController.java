package com.campanha_insentivo.controllers;

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

import com.campanha_insentivo.dtos.CampanhaDto;
import com.campanha_insentivo.model.Campanha;
import com.campanha_insentivo.services.CampanhaService;

@RestController
@RequestMapping("/campanha")
public class CampanhaController {
    
    @Autowired
    private CampanhaService campanhaService;

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CampanhaDto> criarCampanha(@RequestBody CampanhaDto dto){
        var campanhaDto = campanhaService.criarCampanha(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(campanhaDto);
    }

    @RequestMapping(
        method = RequestMethod.GET, 
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<CampanhaDto> findAll() throws Exception{
        return campanhaService.findAll();
    }
}
