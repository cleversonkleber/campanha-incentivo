package com.campanha_incentivo.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;


@Entity
@Table(name = "PARTICIPANTE")
public class Participante implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME_COMPLETO", nullable = false, length = 45)
    private String nome_completo;


    @Column(name = "CPF", nullable = false, length = 255, unique = true)
    private String cpf;

    @Column(name = "EMAIL", nullable = false, length = 255, unique = true)
    @Email
    private String email;

    
    public Participante() {
    }

    public Participante(Long id, String nome_completo, String cpf, String email) {
        this.id = id;
        this.nome_completo = nome_completo;
        this.cpf = cpf;
        this.email = email;
    }

    public Participante(String nome_completo, String cpf, String email) {
         this.nome_completo = nome_completo;
        this.cpf = cpf;
        this.email = email;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
        return "Participante [id=" + id + ", nome=" + nome_completo + ", cpf=" + cpf + ", email=" + email + "]";
    }

    public Campanha criarParticipante(Participante participante) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'criarParticipante'");
    }

    public List<Participante> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }


    
    
}
