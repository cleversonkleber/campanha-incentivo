package com.campanha_incentivo.services;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.campanha_incentivo.configs.TokenConfig;
import com.campanha_incentivo.configs.UsuarioDetails;
import com.campanha_incentivo.dtos.request.LoginRequestDTO;
import com.campanha_incentivo.dtos.request.RegistroUsuarioRequestDto;
import com.campanha_incentivo.dtos.response.RegistroUsuarioResponseDto;
import com.campanha_incentivo.entities.GrupoAcessoEntity;
import com.campanha_incentivo.entities.UsuarioEntity;
import com.campanha_incentivo.exception.handler.ResourceNotFoundException;
import com.campanha_incentivo.mapper.UsuarioMapper;
import com.campanha_incentivo.repositories.GrupoAcessoRepository;
import com.campanha_incentivo.repositories.UsuarioRepository;






@Service
public class UsuarioService {

    private Logger logger = Logger.getLogger(UsuarioService.class.getName());

    private UsuarioRepository usuarioRepository;
    
    private UsuarioMapper mapper;
    
    private final PasswordEncoder passwordEncoder;
    
    private final GrupoAcessoRepository acessoRepository;
    private final TokenConfig tokenConfig;
    private final AuthenticationManager authenticationManager;

    
	public UsuarioService(UsuarioRepository usuarioRepository, 
							UsuarioMapper mapper,
							PasswordEncoder passwordEncoder, 
							TokenConfig tokenConfig, 
							GrupoAcessoRepository acessoRepository, AuthenticationManager authenticationManager) {
		this.passwordEncoder = passwordEncoder;
		this.usuarioRepository = usuarioRepository;
		this.mapper = mapper;
		this.acessoRepository = acessoRepository;
		this.tokenConfig = tokenConfig;
		this.authenticationManager = authenticationManager;
	}


	public RegistroUsuarioResponseDto criarUsuario(RegistroUsuarioRequestDto dto) {
	        
	        if(usuarioRepository.findByEmail(dto.email()).isPresent()) {
	        	throw new RuntimeException("Email Ja cadastrado");
	        }
	    	UsuarioEntity novoUsuario = new UsuarioEntity();
	    	novoUsuario.setEmail(dto.email());
	    	novoUsuario.setNome(dto.nome());
	    	novoUsuario.setSobreNome(dto.sobreNome());
	    	novoUsuario.setCpf(dto.cpf());
	    	novoUsuario.setTelefone1(dto.telefone1());
	    	novoUsuario.setTelefone2(dto.telefone2());
	    	novoUsuario.setSenha(passwordEncoder.encode(dto.senha())); 
	        Optional<GrupoAcessoEntity> acessoEntity =  acessoRepository.findByNome(dto.tipoAcesso());
	        
	        if (acessoEntity.isPresent()){
	        	novoUsuario.getGruposAcesso().add(acessoEntity.get());
	            
	        }else{
	        	Optional<GrupoAcessoEntity> ruleUsuario =  acessoRepository.findByNome("ROLE_USUARIO");
	        	novoUsuario.getGruposAcesso().add(ruleUsuario.get());
	        }
	        UsuarioEntity userSalvo = usuarioRepository.save(novoUsuario);
	        
	        return new RegistroUsuarioResponseDto(userSalvo.getNome(), userSalvo.getEmail(), userSalvo.getCpf());
	        
	        
	        
	    }
	
	
	public List<RegistroUsuarioRequestDto> findAll() {
	        logger.info("Listando todos os participantes.");
	       return usuarioRepository.findAll()
	              .stream()
	              .map(mapper::toDto)
	              .collect(Collectors.toList());
	   }   
	
    public void delete(Long id) {
        logger.info("Deletar participante!");

        var participante = usuarioRepository.findById(id)
                            .orElseThrow(()-> new ResourceNotFoundException("Id não encontrado"));
        usuarioRepository.delete(participante);
    }
    
    public RegistroUsuarioRequestDto update(RegistroUsuarioRequestDto dto) {
        logger.info("Atualizar participante!");
        Optional<UsuarioEntity> optional = usuarioRepository.findByCpf(dto.cpf());
         if (!optional.isPresent()) {
            logger.info("Participante não existe com o cpf: "+ dto.cpf());
        }
        UsuarioEntity usuario = optional
                        .orElseThrow(() -> new ResourceNotFoundException("Não existe um participante com o cpf: "+ dto.cpf()));
        usuario.setSenha(dto.senha());
        
        
        return mapper.toDto(usuario);

    }

    public RegistroUsuarioRequestDto findByCpf(RegistroUsuarioRequestDto dto){
        Optional<UsuarioEntity> optional = usuarioRepository.findByCpf(dto.cpf());
        UsuarioEntity usuario = optional
                        .orElseThrow(() -> new ResourceNotFoundException("Não existe um participante com o cpf: "+ dto.cpf()));
        return mapper.toDto(usuario);
    }


	public String login(LoginRequestDTO dto) {
		 UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
		         dto.email(), 
                 dto.senha()
             );
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		UsuarioDetails usuarioDetails = (UsuarioDetails) authentication.getPrincipal();
		UsuarioEntity user = usuarioDetails.getUsuarioEntity();

   		String token =  tokenConfig.generateTocken(user);
		
		return token;
	}


	

}
















