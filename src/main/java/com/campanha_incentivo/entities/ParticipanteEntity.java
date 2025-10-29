package com.campanha_incentivo.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "TB_PARTICIPANTE")
public class ParticipanteEntity implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_participante;

    @OneToOne
    @JoinColumn(name = "fk_usuario_id", nullable = false)
    private UsuarioEntity entityUsuarioEntity;
    
    @ManyToMany(mappedBy = "participantes")
    private Set<GrupoAcessoEntity> gruposAcesso = new HashSet<>();

    @OneToMany(mappedBy = "participante")
	private List<EventoEntity> eventos; 

    
    public ParticipanteEntity() {
    }

    
	public ParticipanteEntity(Long id_participante, UsuarioEntity entityUsuarioEntity, List<EventoEntity> eventos) {
		super();
		this.id_participante = id_participante;
		this.entityUsuarioEntity = entityUsuarioEntity;
		this.eventos = eventos;
	}
	
 
	public Set<GrupoAcessoEntity> getGruposAcesso() {
		return gruposAcesso;
	}


	public void setGruposAcesso(Set<GrupoAcessoEntity> gruposAcesso) {
		this.gruposAcesso = gruposAcesso;
	}


	public UsuarioEntity getEntityUsuarioEntity() {
		return entityUsuarioEntity;
	}

	public void setEntityUsuarioEntity(UsuarioEntity entityUsuarioEntity) {
		this.entityUsuarioEntity = entityUsuarioEntity;
	}

	public Long getId_participante() {
		return id_participante;
	}

	public void setId_participante(Long id_participante) {
		this.id_participante = id_participante;
	}

     public List<EventoEntity> getEventos() {
		return eventos;
	}

	public void setEventos(List<EventoEntity> eventos) {
		this.eventos = eventos;
	}


 


    
    
}
