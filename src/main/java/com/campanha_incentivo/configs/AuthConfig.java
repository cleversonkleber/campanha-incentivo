package com.campanha_incentivo.configs;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.campanha_incentivo.entities.UsuarioEntity;
import com.campanha_incentivo.repositories.UsuarioRepository;

@Service
public class AuthConfig  implements UserDetailsService{

	private final UsuarioRepository repository;
    
    public AuthConfig(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	UsuarioEntity entity = repository.findByEmail(username).orElseThrow(
                ()-> new UsernameNotFoundException(username)
        );
    	return new UsuarioDetails(entity);
    }

}
