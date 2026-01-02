package com.desafiopedidos.demo.infra.security;

import com.desafiopedidos.demo.repositories.UsuarioRepository;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter { //Roda uma vez por requisição

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException { //Metodo executado em toda requisição
        var tokenJWT = recuperarToken(request); //Tenta pegar o token do header

        String uri = request.getRequestURI();
        if (uri.equals("/login")) {
            filterChain.doFilter(request, response);
            return;
        } //Ignora o endpoint do Login

        if (tokenJWT != null){
           var subject = tokenService.getSubject(tokenJWT);
            var usuario = repository.findByLogin(subject);

            var authentication = new UsernamePasswordAuthenticationToken(usuario,
                    null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
      }

        filterChain.doFilter(request, response);

    }

    private String recuperarToken(HttpServletRequest request) {
        var autorizationHeader = request.getHeader("Authorization"); //Lê o Header Authorization
        if (autorizationHeader != null) {
            return autorizationHeader.replace("Bearer", "").trim(); //Vai remover o Bearer
        }
        return null;
    }
}
