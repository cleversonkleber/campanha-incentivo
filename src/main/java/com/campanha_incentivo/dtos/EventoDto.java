package com.campanha_incentivo.dtos;

import java.time.LocalDateTime;

import com.campanha_incentivo.entities.TipoEvento;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EventoDto(
		
		@NotNull(message = "O ID do evento é obrigatório para atualização.")
        Long id,
        
        @NotNull(message = "A data do evetnto é obrigatória.")
        LocalDateTime dataHoraOcorrencia,

        @NotNull(message = "O Tipo do envento é obrigatório.")
        TipoEvento tipoEvento,
        
        @NotNull(message = "A data de início é obrigatória.")
        Double valor,

        @NotNull(message = "A descrição do evento é obrigatória.")
        @Size(max = 500, message = "A descrição não pode exceder 500 caracteres.")
        String descricao,

        @NotNull(message = "Pontos gerados é obrigatória.")
        Double pontosGerados,

        @NotNull(message = "Participante é obrigatória.")
        String cpf_participante,

        @NotNull(message = "Campanha obrigatória.")
        String nomeCampanha

 ){}

