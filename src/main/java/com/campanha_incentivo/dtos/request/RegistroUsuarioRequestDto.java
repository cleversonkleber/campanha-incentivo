package com.campanha_incentivo.dtos.request;

import jakarta.validation.constraints.NotBlank;


public record RegistroUsuarioRequestDto(

	    @NotBlank(message = "O nome do usuário é obrigatório.")
	    String nome,
	    
	    @NotBlank(message = "O sobrenome do usuário é obrigatório.")
	    String sobreNome,

	    @NotBlank(message = "O email do usuário é obrigatório.")
	    String email,
	    
	    @NotBlank(message = "O CPF do usuário é obrigatório.")
	    String cpf,
	    
	    @NotBlank(message = "A senha do usuário é obrigatória.")
	    String senha,
	    
	    @NotBlank(message = "O telefone principal é obrigatório.")
	    String telefone1,

	    String telefone2, 

	    String tipoAcesso
		) {

}
