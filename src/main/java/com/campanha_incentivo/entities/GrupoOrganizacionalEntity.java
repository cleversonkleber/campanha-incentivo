package com.campanha_incentivo.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_GRUPO_ORGANIZACIONAL")
public class GrupoOrganizacionalEntity implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @Column(unique = true, nullable = false)
    private String nome;
    
    @ManyToMany
    @JoinTable(
        name = "TB_PARTICIPANTE_GRUPO_ORGANIZACIONAL", 
        joinColumns = @JoinColumn(name = "fk_grupo_id"), 
        inverseJoinColumns = @JoinColumn(name = "fk_participante_id")
    )
    private Set<UsuarioEntity> usuarioEntities = new HashSet<>();
    
    

	public GrupoOrganizacionalEntity() {
		super();
	}

	public Set<UsuarioEntity> getUsuarioEntities() {
		return usuarioEntities;
	}


	public void setUsuarioEntities(Set<UsuarioEntity> usuarioEntities) {
		this.usuarioEntities = usuarioEntities;
	}


	public GrupoOrganizacionalEntity( String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
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
        GrupoOrganizacionalEntity that = (GrupoOrganizacionalEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }




}
