package com.campanha_insentivo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ParticipanteDTO(
    
    @NotBlank(message = "O nome do participante é obrigatório.") 
    @Size(min = 2, max = 45, message = "O nome deve ter entre 3 e 100 caracteres.")
    String nome_completo,
    
    @NotBlank(message = "O nome do participante é obrigatório.") 
    @Size(min = 11, max = 14, message = "O CPF deve conter no mínio 11 e 14 caracteres.")
    String cpf,
    
    @NotBlank(message = "O nome do participante é obrigatório.") 
    @Size(min = 5, max = 45, message = "O nome deve ter entre 3 e 250 caracteres.")
    String email
) {}
