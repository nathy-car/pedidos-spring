package com.desafiopedidos.demo.config;

import com.desafiopedidos.demo.entities.usuario.Usuario;
import com.desafiopedidos.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Configuration
public class UsuarioConfig implements CommandLineRunner{

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        String hash = passwordEncoder.encode("1234");
        Usuario user1 = new Usuario(null, "Nathhy@brad", hash);

        repository.saveAll(Arrays.asList(user1));
    }
}
