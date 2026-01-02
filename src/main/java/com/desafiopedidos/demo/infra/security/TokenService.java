package com.desafiopedidos.demo.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.desafiopedidos.demo.entities.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")//Injeta o valor que foi definido na application
    private String secret;

    public String gerarToken(Usuario usuario){
        try {
            var algoritimo = Algorithm.HMAC256(secret); //Algoritimo para criptografar
            return JWT.create()
                    .withIssuer("API Voll.med") //Identifica quem emitiu o token
                    .withSubject(usuario.getLogin()) //Quem é o usuário que vai pertencer o token
                    .withExpiresAt(dataExpiracao()) //Quando vai expirar
                    .sign(algoritimo); //Gera a string final
        } catch (JWTCreationException exception){
           throw new RuntimeException("Erro ao gerar token", exception);
        }
    }

    public String getSubject(String tokenJWT) { //Valida o token
        try {
            var algoritimo = Algorithm.HMAC256(secret); //Mesma chave criada na criação do token
            return JWT.require(algoritimo) //Qual algoritimo vai ser usado
                    .withIssuer("API Voll.med") //Confere se foi emitido pela api(precisa ser o mesmo nome)
                    .build()
                    .verify(tokenJWT)//Verifica tudo
                    .getSubject(); //Retorna o login do usuário
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("TokenJWT inválido ou expirado");
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
