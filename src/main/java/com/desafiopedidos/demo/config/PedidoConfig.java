package com.desafiopedidos.demo.config;

import com.desafiopedidos.demo.entities.Pedido;
import com.desafiopedidos.demo.repositories.PedidoRepository;
import com.desafiopedidos.demo.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration //Mostrar pro Spring que é uma Configuration
public class PedidoConfig {

    @Autowired //Serve para injetar o repositório na classe
    private PedidoRepository pedidoRepository;

    @Bean //Cria um objeto manualmente, normalmente utilizado em Configuration
    CommandLineRunner carregarPedidos() { //CommandLineRunner executa o código na inicialização
        return args -> {
            Pedido f1 = new Pedido(null, "Arroz e batata", true);
            Pedido f2 = new Pedido(null, "Arroz e feijao", true);

            pedidoRepository.save(f1);
            pedidoRepository.save(f2);

            System.out.println(">>> PEDIDOS SALVOS <<<");
        };
    }
}


