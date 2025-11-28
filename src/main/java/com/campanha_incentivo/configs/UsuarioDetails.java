package com.campanha_incentivo.configs;



import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.campanha_incentivo.entities.UsuarioEntity;

public class UsuarioDetails implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	 private final UsuarioEntity usuario;

	    public UsuarioDetails(UsuarioEntity usuario) {
	        this.usuario = usuario;
	    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  usuario.getGruposAcesso().stream().map(r-> new SimpleGrantedAuthority(r.getNome())).toList();
    }
    
    public UsuarioEntity getUsuarioEntity() {
        return this.usuario;
    }


	@Override
	public String getPassword() {
		return usuario.getSenha();
	}

	@Override
	public String getUsername() {
		return usuario.getEmail();
	}
	
    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
	

}
