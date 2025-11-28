package com.campanha_incentivo.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.campanha_incentivo.entities.GrupoAcessoEntity;
import com.campanha_incentivo.entities.UsuarioEntity;
import com.campanha_incentivo.repositories.GrupoAcessoRepository;
import com.campanha_incentivo.repositories.UsuarioRepository;

@Configuration
public class DataLoader {

    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String ROLE_GESTOR = "ROLE_GESTOR";
    private static final String ROLE_PARTICIPANTE = "ROLE_PARTICIPANTE";
    private static final String ROLE_USUARIO = "ROLE_USUARIO";

    DataLoader(GrupoAcessoRepository grupoAcessoRepository) {
    }

    @Bean
    CommandLineRunner initDatabase(
            UsuarioRepository usuarioRepository,
            GrupoAcessoRepository grupoAcessoRepository,
            PasswordEncoder passwordEncoder) {
        
        return args -> {
            
            GrupoAcessoEntity adminRole = grupoAcessoRepository.findByNome(ROLE_ADMIN)
            		.orElseGet(() -> grupoAcessoRepository.save(new GrupoAcessoEntity(ROLE_ADMIN)));
            
            GrupoAcessoEntity gestorRole = grupoAcessoRepository.findByNome(ROLE_GESTOR)
                    .orElseGet(() -> grupoAcessoRepository.save(new GrupoAcessoEntity(ROLE_GESTOR)));

            GrupoAcessoEntity participanteRole = grupoAcessoRepository.findByNome(ROLE_PARTICIPANTE)
                    .orElseGet(() -> grupoAcessoRepository.save(new GrupoAcessoEntity(ROLE_PARTICIPANTE)));
            GrupoAcessoEntity usuarioRole = grupoAcessoRepository.findByNome(ROLE_USUARIO)
                    .orElseGet(() -> grupoAcessoRepository.save(new GrupoAcessoEntity(ROLE_USUARIO)));
            
 

            if (usuarioRepository.findByEmail("admin@incentivo.com").isEmpty()) {
                UsuarioEntity adminUser = new UsuarioEntity();
                adminUser.setNome("Administrador");
                adminUser.setSobreNome("Admin");
                adminUser.setEmail("admin@incentivo.com");
                adminUser.setCpf("00000000000");
                adminUser.setTelefone1("00000000000");
                adminUser.setTelefone2("00000000000");
                adminUser.setSenha(passwordEncoder.encode("adminCampanha@2025")); 
                
                adminUser.getGruposAcesso().add(adminRole);
                adminUser.getGruposAcesso().add(gestorRole);
                
                
                usuarioRepository.save(adminUser);
                System.out.println("Usuário Admin inicializado e salvo com sucesso!");
               
            }

        };
        
    }
}