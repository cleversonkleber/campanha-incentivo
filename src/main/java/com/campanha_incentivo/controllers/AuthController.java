package com.campanha_incentivo.controllers;

import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campanha_incentivo.dtos.request.LoginRequestDTO;
import com.campanha_incentivo.dtos.request.RegistroUsuarioRequestDto;
import com.campanha_incentivo.dtos.response.RegistroUsuarioResponseDto;
import com.campanha_incentivo.dtos.response.TokenResponseDTO;
import com.campanha_incentivo.services.UsuarioService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

		
		private Logger logger = Logger.getLogger(AuthController.class.getName());
	 	private final UsuarioService usuarioService;

	 	public AuthController(UsuarioService usuarioService) {
	         this.usuarioService = usuarioService;
	    }

	    @PostMapping("/login")
	    public ResponseEntity<TokenResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
	        String token = usuarioService.login(dto);

	        return ResponseEntity.ok(new TokenResponseDTO(token));
	    }
	    
	    @PostMapping("/register")
	    public ResponseEntity<RegistroUsuarioResponseDto> register(@Valid @RequestBody RegistroUsuarioRequestDto dto){
	    	RegistroUsuarioResponseDto dtoSalvo = usuarioService.criarUsuario(dto);
	        return ResponseEntity.status(HttpStatus.CREATED)
	        		.body(dtoSalvo);
	    } 
	
	
}
