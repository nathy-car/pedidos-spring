package com.desafiopedidos.demo.service;

import com.desafiopedidos.demo.dto.PedidoDTO;
import com.desafiopedidos.demo.entities.Pedido;
import com.desafiopedidos.demo.repositories.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoService {

    @Autowired
    PedidoRepository pedido;


    public List<PedidoDTO> listarTudo() {
        List<Pedido> pedidos = pedido.findAll();

        return pedidos.stream()
                .map(p -> new PedidoDTO(
                        p.getPedido(),
                        p.getConfirmado()
                ))
                .toList();
    }

    public Pedido criarPedido(Pedido pedidoCriado) {
        return pedido.save(pedidoCriado);
    }

    public Pedido atualizarPedido(Pedido pedidoAtualizado, Long id) {
        if (pedido.findById(id) != null) {
            pedidoAtualizado.setId(id);
            return pedido.save(pedidoAtualizado);
        }
        return null;
    }

    public List<Pedido> retornaPeloNome(String nome) {
        return pedido.findByPedidoContainingIgnoreCase(nome);
    }

    public Pedido retornaPeloId(Long id) {
        return pedido.findById(id).get();
    }

    public void deletarPeloId(Long id) {
        pedido.deleteById(id);
}
}
