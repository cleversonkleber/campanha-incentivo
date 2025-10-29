package com.campanha_incentivo.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "TB_GRUPO_ACESSO")
public class GrupoAcessoEntity implements GrantedAuthority {
	
    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @Column(unique = true, nullable = false)
    private String nome;
    
    @ManyToMany(mappedBy = "gruposAcesso")
    private Set<UsuarioEntity> usuarios = new HashSet<>();
    
	public GrupoAcessoEntity() {}


	public GrupoAcessoEntity( String nome) {
		this.nome = nome;
	}
	

	@Override
    @Transient 
    public String getAuthority() {
        return this.nome;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrupoAcessoEntity that = (GrupoAcessoEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }




}
