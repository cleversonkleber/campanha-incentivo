package com.campanha_incentivo.configs;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.campanha_incentivo.entities.GrupoAcessoEntity;
import com.campanha_incentivo.entities.UsuarioEntity;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.time.Instant;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class TokenConfig {

	private String secret = "secret";

    public String generateTocken(UsuarioEntity user){
        Algorithm algoritmo = Algorithm.HMAC256(secret);
        return JWT.create()
                .withClaim("userId", user.getId_usuario())
                .withClaim("roles", user.getGruposAcesso().stream().map(GrupoAcessoEntity::getNome).toList())
                .withSubject(user.getEmail())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .sign(algoritmo);
    }

    public Optional<JWTUserData> validateToken(String token){

        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT decodedJWT = JWT.require(algorithm)
                                    .build()
                                    .verify(token);

            return Optional.of(new JWTUserData(
			            		decodedJWT.getClaim("userId").asLong(),
			            		decodedJWT.getSubject(),
			            		decodedJWT.getClaim("roles").asList(String.class)
            				)
            			); 
        }catch (JWTVerificationException ex){
            return Optional.empty();
        }
        
    }


}
