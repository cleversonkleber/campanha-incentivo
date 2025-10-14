package com.campanha_incentivo.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "CAMPANHA")
public class Campanha implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id_campanha;


    @Column(name = "NOME_CAMPANHA", nullable = false, unique = true)
    private String nome;

    @Column(name = "DATA_INICIO", nullable = false)
    private LocalDateTime dataInicio;

    @Column(name = "DATA_FIM", nullable = false)
    private LocalDateTime dataFim;

    @Column(name = "DESCRICAO", nullable = false, length = 255)
    private String descricao;

    @OneToMany
    @JoinTable(name = "TB_EVENTO",
	     joinColumns = @JoinColumn(name = "id_evento"),
	     inverseJoinColumns = @JoinColumn(name = "id_campanha"))
    @Column(name = "EVENTO",nullable = true)
    private List<Evento> eventos; 


    public Campanha() {
    }


	public Campanha(Long id_campanha, String nome, LocalDateTime dataInicio, LocalDateTime dataFim, String descricao,
			List<Evento> eventos) {
		this.id_campanha = id_campanha;
		this.nome = nome;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.descricao = descricao;
		this.eventos = eventos;
	}


	public Long getId_campanha() {
		return id_campanha;
	}


	public void setId_campanha(Long id_campanha) {
		this.id_campanha = id_campanha;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public LocalDateTime getDataInicio() {
		return dataInicio;
	}


	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}


	public LocalDateTime getDataFim() {
		return dataFim;
	}


	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public List<Evento> getEventos() {
		return eventos;
	}


	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_campanha == null) ? 0 : id_campanha.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Campanha other = (Campanha) obj;
		if (id_campanha == null) {
			if (other.id_campanha != null)
				return false;
		} else if (!id_campanha.equals(other.id_campanha))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Campanha [id_campanha=" + id_campanha + ", nome=" + nome + ", dataInicio=" + dataInicio + ", dataFim="
				+ dataFim + ", descricao=" + descricao + ", eventos=" + eventos + "]";
	}
    
   

  


}
