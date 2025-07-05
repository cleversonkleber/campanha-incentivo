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
    public Campanha criarCampanha(@RequestBody Campanha campanha){
        return campanhaService.criarCampanha(campanha);
    }

    @RequestMapping(
        method = RequestMethod.GET, 
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Campanha> findAll() throws Exception{
        return campanhaService.findAll();
    }
}
