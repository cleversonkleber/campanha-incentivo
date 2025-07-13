package com.campanha_insentivo.dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CampanhaDto(
        @NotBlank(message = "O nome da campanha é obrigatório.") 
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
        String nome,

        @NotNull(message = "A data de início é obrigatória.")
        @FutureOrPresent(message = "A data de início não pode ser no passado.")
        LocalDateTime dataInicio,

        @NotNull(message = "A data de término é obrigatória.")
        @FutureOrPresent(message = "A data de término não pode ser no passado.") 
        LocalDateTime dataFim,

        @NotBlank(message = "A descrição é obrigatória.")
        @Size(max = 255, message = "A descrição não pode exceder 255 caracteres.")
        String descricao
 ){}

