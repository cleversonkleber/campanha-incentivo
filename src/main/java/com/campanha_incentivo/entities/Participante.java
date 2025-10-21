package com.campanha_incentivo.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;


@Entity
@Table(name = "PARTICIPANTE")
public class Participante implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_participante;

    @Column(name = "NOME_COMPLETO", nullable = false, length = 45)
    private String nome_completo;


    @Column(name = "CPF", nullable = false, length = 255, unique = true)
    private String cpf;

    @Column(name = "EMAIL", nullable = false, length = 255, unique = true)
    @Email
    private String email;

    @OneToMany(mappedBy = "participante")
	private List<Evento> eventos; 

    
    public Participante() {
    }

    public Participante(Long id_participante, String nome_completo, String cpf, String email, List<Evento> eventos) {
        this.id_participante = id_participante;
        this.nome_completo = nome_completo;
        this.cpf = cpf;
        this.email = email;
        this.eventos = eventos;
    }

    public Participante(String nome_completo, String cpf, String email) {
         this.nome_completo = nome_completo;
        this.cpf = cpf;
        this.email = email;
    }
    

    public Long getId_participante() {
		return id_participante;
	}

	public void setId_participante(Long id_participante) {
		this.id_participante = id_participante;
	}

	public String getNome_completo() {
        return nome_completo;
    }
    public void setNome_completo(String nome_completo) {
        this.nome_completo = nome_completo;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

     public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}


    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Participante other = (Participante) obj;
        return Objects.equals(cpf, other.cpf); 
    }

    @Override
    public String toString() {
        return "Participante [id=" + id_participante + ", nome=" + nome_completo + ", cpf=" + cpf + ", email=" + email + "]";
    }

 


    
    
}
