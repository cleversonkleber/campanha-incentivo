package com.campanha_incentivo.services;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.campanha_incentivo.dtos.UsuarioDTO;
import com.campanha_incentivo.entities.GrupoAcessoEntity;
import com.campanha_incentivo.entities.UsuarioEntity;
import com.campanha_incentivo.exception.handler.ResourceExistsException;
import com.campanha_incentivo.exception.handler.ResourceNotFoundException;
import com.campanha_incentivo.mapper.UsuarioMapper;
import com.campanha_incentivo.repositories.GrupoAcessoRepository;
import com.campanha_incentivo.repositories.UsuarioRepository;




@Service
public class UsuarioService implements UserDetailsService{

    private Logger logger = Logger.getLogger(UsuarioService.class.getName());

    private UsuarioRepository usuarioRepository;
    
    private UsuarioMapper mapper;
    
    private final PasswordEncoder passwordEncoder;
    
    private final GrupoAcessoRepository acessoRepository;

    
	public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper mapper,PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
		this.usuarioRepository = usuarioRepository;
		this.mapper = mapper;
		this.acessoRepository = null;
	}


	public UsuarioDTO criarUsuario(UsuarioDTO dto) {
	        
	        if(usuarioRepository.findByEmail(dto.email()).isPresent()) {
	        	throw new RuntimeException("Email Ja cadastrado");
	        }
	        GrupoAcessoEntity roleParticipante = acessoRepository.findByNome("ROLE_USUARIO")
	        		.orElseThrow(()-> new RuntimeException("Role padrão não encontrado"));
	        
	        String senhaCripto = passwordEncoder.encode(dto.senha());
	        UsuarioEntity novoUsuario = mapper.toEntity(dto);
	        novoUsuario.setSenha(senhaCripto);
	        novoUsuario.getGruposAcesso().add(roleParticipante);
	        return mapper.toDto(usuarioRepository.save(novoUsuario));
	        
	        
	        
	    }
	
	public UsuarioDTO criarGestor(UsuarioDTO dto) {
        
        if(usuarioRepository.findByEmail(dto.email()).isPresent()) {
        	throw new RuntimeException("Email Ja cadastrado");
        }
        GrupoAcessoEntity roleParticipante = acessoRepository.findByNome("ROLE_GESTOR")
        		.orElseThrow(()-> new RuntimeException("Role padrão não encontrado"));
        
        String senhaCripto = passwordEncoder.encode(dto.senha());
        UsuarioEntity novoUsuario = mapper.toEntity(dto);
        novoUsuario.setSenha(senhaCripto);
        novoUsuario.getGruposAcesso().add(roleParticipante);
        return mapper.toDto(usuarioRepository.save(novoUsuario));
        
    }
	
	public List<UsuarioDTO> findAll() {
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
    
    public UsuarioDTO update(UsuarioDTO dto) {
        logger.info("Atualizar participante!");
        Optional<UsuarioEntity> optional = usuarioRepository.findById(dto.id());
         if (!optional.isPresent()) {
            logger.info("Participante não existe com o cpf: "+ dto.cpf());
        }
        UsuarioEntity usuario = optional
                        .orElseThrow(() -> new ResourceNotFoundException("Não existe um participante com o cpf: "+ dto.cpf()));
        usuario.setSenha(dto.senha());
        
        
        return mapper.toDto(usuario);

    }

    public UsuarioDTO findByCpf(UsuarioDTO dto){
        Optional<UsuarioEntity> optional = usuarioRepository.findByCpf(dto.cpf());
        UsuarioEntity usuario = optional
                        .orElseThrow(() -> new ResourceNotFoundException("Não existe um participante com o cpf: "+ dto.cpf()));
        return mapper.toDto(usuario);
    }


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
    

}
















