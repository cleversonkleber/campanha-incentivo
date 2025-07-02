package com.campanha_insentivo.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "CAMPANHA")
public class Campanha implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da campanha é obrigatório.") 
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
    @Column(name = "NOME_CAMPANHA", nullable = false, unique = true)
    private String nome;

    @Column(name = "DATA_INICIO", nullable = false)
    @NotNull(message = "A data de início é obrigatória.")
    @FutureOrPresent(message = "A data de início não pode ser no passado.")
    private LocalDateTime dataInicio;

    @Column(name = "DATA_FIM", nullable = false)
    @NotNull(message = "A data de término é obrigatória.")
    @FutureOrPresent(message = "A data de término não pode ser no passado.") 
    private LocalDateTime dataFim;

    @Column(name = "DESCRICAO", nullable = false, length = 255)
    @NotBlank(message = "A descrição é obrigatória.")
    @Size(max = 255, message = "A descrição não pode exceder 255 caracteres.")
    private String descricao;

    public Campanha() {
    }
    
    public Campanha(Long id, String nome, LocalDateTime dataInicio, LocalDateTime dataFim, String descricao) {
        this.id = id;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.descricao = descricao;
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



    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }



    @Override
    public boolean equals(Object obj) {
       if (this == obj) return true;
       if (obj == null || getClass() != obj.getClass()) return false;
       Campanha other = (Campanha) obj;
       return Objects.equals(nome, other.nome); 
    }

    


}
