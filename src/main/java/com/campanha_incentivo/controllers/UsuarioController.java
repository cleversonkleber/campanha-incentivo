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

import com.campanha_incentivo.dtos.UsuarioDTO;
import com.campanha_incentivo.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    private Logger logger = Logger.getLogger(UsuarioController.class.getName());

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UsuarioDTO> criarParticipante(@RequestBody UsuarioDTO dto){
        logger.info("Criar usuário!");
        UsuarioDTO usuarioDTO = usuarioService.criarUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);
    }

    @GetMapping( 
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<UsuarioDTO> findAll() throws Exception{
        logger.info("Listar todos os usuários!");

        return usuarioService.findAll();
    }

    @DeleteMapping(
        value = "{id}"
    )
    public ResponseEntity<?> delete(@PathVariable Long id){
         logger.info("Deletar usuário!");
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UsuarioDTO> update(@RequestBody UsuarioDTO dto){
        logger.info("Atualizar usuário!");
        try{
            return ResponseEntity.ok(usuarioService.update(dto));
        }catch(Exception e){
            logger.info("Erro ao atualizar usuário! "+e);
            return ResponseEntity.notFound().<UsuarioDTO>build();
        }
    }
    
}
