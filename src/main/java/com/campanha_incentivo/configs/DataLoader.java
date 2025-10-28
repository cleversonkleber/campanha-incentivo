package com.campanha_incentivo.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.campanha_incentivo.entities.GropsAcessoEntity;
import com.campanha_incentivo.entities.UsuarioEntity;
import com.campanha_incentivo.repositories.GrupoAcessoRepository;
import com.campanha_incentivo.repositories.UsuarioRepository;

@Configuration
public class DataLoader {

    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String ROLE_GESTOR = "ROLE_GESTOR";
    private static final String ROLE_PARTICIPANTE = "ROLE_PARTICIPANTE";

    DataLoader(GrupoAcessoRepository grupoAcessoRepository) {
    }

    @Bean
    CommandLineRunner initDatabase(
            UsuarioRepository usuarioRepository,
            GrupoAcessoRepository grupoAcessoRepository,
            PasswordEncoder passwordEncoder) {
        
        return args -> {
            
            GropsAcessoEntity adminRole = grupoAcessoRepository.findByNome(ROLE_ADMIN)
            		.orElseGet(() -> grupoAcessoRepository.save(new GropsAcessoEntity(ROLE_ADMIN)));
            
            GropsAcessoEntity gestorRole = grupoAcessoRepository.findByNome(ROLE_GESTOR)
                    .orElseGet(() -> grupoAcessoRepository.save(new GropsAcessoEntity(ROLE_GESTOR)));

            GropsAcessoEntity participanteRole = grupoAcessoRepository.findByNome(ROLE_PARTICIPANTE)
                    .orElseGet(() -> grupoAcessoRepository.save(new GropsAcessoEntity(ROLE_PARTICIPANTE)));

            if (usuarioRepository.findByEmail("admin@incentivo.com").isEmpty()) {
                UsuarioEntity adminUser = new UsuarioEntity();
                adminUser.setNome("Administrador");
                adminUser.setEmail("admin@incentivo.com");
                adminUser.setCpf("00000000000");
                adminUser.setSenha(passwordEncoder.encode("adminCampanha@2025")); 
                
                adminUser.getGruposAcesso().add(adminRole);
                adminUser.getGruposAcesso().add(gestorRole);
                
                usuarioRepository.save(adminUser);
               
            }
        };
    }
}