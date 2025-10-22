package com.campanha_incentivo.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_EVENTO")
public class EventoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id_envento;

    @Column(name = "DATAHORA_OCORRENCIA", nullable = false)
    private LocalDateTime dataHoraOcorrencia;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "TIPO_EVENTO", nullable = false)
    private TipoEvento tipoEvento;
    @Column(name = "VALOR", nullable = false)
    private Double valor;
    @Column(name = "DESCRICAO_EVENTO", nullable = false)
    private String descricao;
    @Column(name = "PONTOS_GERADOS", nullable = false)
    private Double pontosGerados;

    @ManyToOne
    @JoinColumn(name = "id_participante")
    private ParticipanteEntity participante;
    
    @ManyToOne
    @JoinColumn(name = "id_campanha", nullable = false)
    private CampanhaEntity campanha;


    public EventoEntity() {
    }


	public EventoEntity(Long id_envento, LocalDateTime dataHoraOcorrencia, TipoEvento tipoEvento, Double valor,
			String descricao, Double pontosGerados, ParticipanteEntity participante, CampanhaEntity campanha) {
		this.id_envento = id_envento;
		this.dataHoraOcorrencia = dataHoraOcorrencia;
		this.tipoEvento = tipoEvento;
		this.valor = valor;
		this.descricao = descricao;
		this.pontosGerados = pontosGerados;
		this.participante = participante;
		this.campanha = campanha;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Long getId_envento() {
		return id_envento;
	}


	public void setId_envento(Long id_envento) {
		this.id_envento = id_envento;
	}


	public LocalDateTime getDataHoraOcorrencia() {
		return dataHoraOcorrencia;
	}


	public void setDataHoraOcorrencia(LocalDateTime dataHoraOcorrencia) {
		this.dataHoraOcorrencia = dataHoraOcorrencia;
	}


	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}


	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}


	public Double getValor() {
		return valor;
	}


	public void setValor(Double valor) {
		this.valor = valor;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Double getPontosGerados() {
		return pontosGerados;
	}


	public void setPontosGerados(Double pontosGerados) {
		this.pontosGerados = pontosGerados;
	}


	public ParticipanteEntity getParticipante() {
		return participante;
	}


	public void setParticipante(ParticipanteEntity participante) {
		this.participante = participante;
	}


	public CampanhaEntity getCampanha() {
		return campanha;
	}


	public void setCampanha(CampanhaEntity campanha) {
		this.campanha = campanha;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_envento == null) ? 0 : id_envento.hashCode());
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
		EventoEntity other = (EventoEntity) obj;
		if (id_envento == null) {
			if (other.id_envento != null)
				return false;
		} else if (!id_envento.equals(other.id_envento))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Evento [id_envento=" + id_envento + ", dataHoraOcorrencia=" + dataHoraOcorrencia + ", tipoEvento="
				+ tipoEvento + ", valor=" + valor + ", descricao=" + descricao + ", pontosGerados=" + pontosGerados
				+ ", participante=" + participante + ", campanha=" + campanha + "]";
	}


	


	
    
    
    
    
    
}
