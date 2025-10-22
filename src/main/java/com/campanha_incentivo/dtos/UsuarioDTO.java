package com.campanha_incentivo.dtos;

import jakarta.validation.constraints.NotNull;

public record UsuarioDTO(
				@NotNull(message = "O ID do usuário é obrigatório para atualização.")
			    Long id,
			    @NotNull(message = "O nome do usuário é obrigatório para atualização.")
			    String nome,
			    @NotNull(message = "O sobrenome do usuário é obrigatório para atualização.")
			    String sobreNome,
			    @NotNull(message = "O email do usuário é obrigatório para atualização.")
			    String email,
			    @NotNull(message = "O cpf do usuário é obrigatório para atualização.")
			    String cpf,
			    @NotNull(message = "O senha do usuário é obrigatório para atualização.")
			    String senha,
			    @NotNull(message = "O telone do usuário é obrigatório para atualização.")
			    String telefone1,
			    String telefone2
		
		
		){



}
