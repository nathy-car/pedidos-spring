package com.desafiopedidos.demo.controller;

import com.desafiopedidos.demo.dto.AutenticacaoDTO;
import com.desafiopedidos.demo.entities.usuario.Usuario;
import com.desafiopedidos.demo.infra.security.TokenJWTDTO;
import com.desafiopedidos.demo.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController //Indica que recebe requisição HTTP e retorna JSON
@RequestMapping("/login") //Mostra o começo de todos os endpoints
public class AutentificacaoController {

    @Autowired
    private AuthenticationManager manager; //Quem faz a autenticação; Compara a senha enviada com a do banco de dados
    //Manda exceção de a autenticação falhar



    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody AutenticacaoDTO autenticacaoDTO){
        var autenticationToken = new UsernamePasswordAuthenticationToken(
                autenticacaoDTO.login(),
                autenticacaoDTO.senha()); //Carrega login e senha
        var authentication = manager.authenticate(autenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJWTDTO(tokenJWT));
    }

}
