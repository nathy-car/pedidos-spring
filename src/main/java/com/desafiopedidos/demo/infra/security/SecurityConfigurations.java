package com.desafiopedidos.demo.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration //Define as configurações
@EnableWebSecurity //Ativa o Spring Security
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return
                http.csrf(csrf -> csrf.disable()) //CSRF é proteção para sessão/cookies, como não usa sessão, foi desativado
                        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // STATELESS não guarda login, é preciso do token
                        .authorizeHttpRequests(req -> {
                            req.requestMatchers("/login").permitAll(); // Endpoint de login pode acessar sem autenticação
                            req.anyRequest().authenticated(); // Todas as outras requisições precisam ser autenticadas
                        })
                        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) //Primeiro tem que ser lido o token
                        .build(); //Constrói o SecurityFIlterChain


    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
