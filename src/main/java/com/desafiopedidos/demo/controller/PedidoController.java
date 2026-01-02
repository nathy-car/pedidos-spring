package com.desafiopedidos.demo.controller;

import com.desafiopedidos.demo.dto.PedidoDTO;
import com.desafiopedidos.demo.entities.Pedido;
import com.desafiopedidos.demo.service.PedidoService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedido;

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> retornarPedidos(){
            List<PedidoDTO> mostrar = pedido.listarTudo();
            return ResponseEntity.ok().body(mostrar);
    }

    @PostMapping(value = "/nova_criacao")
       public ResponseEntity<Pedido> criarPedido(@RequestBody @Valid Pedido pedidoCriar){
                Pedido pedidoNovo = pedido.criarPedido(pedidoCriar);
                return ResponseEntity.ok(pedidoNovo);
        }

    @PutMapping(value = "/atualizar/{id}")
    public ResponseEntity<Pedido> atualizarPedido(@RequestBody Pedido pedidoAtualizar,
                                                  @PathVariable Long id){
            Pedido pedidoAtualizado = pedido.atualizarPedido(pedidoAtualizar, id);
            return ResponseEntity.status(HttpStatus.CREATED).body(pedidoAtualizado);
    }

    @GetMapping(value = "/mostrar-por-id/{id}")
    public ResponseEntity<Pedido> mostrarPorId(@PathVariable Long id){
            Pedido pedido1 = pedido.retornaPeloId(id);
            return ResponseEntity.ok(pedido1);
    }

    @DeleteMapping(value = "/deletar-por-id/{id}")
    public ResponseEntity deletarPorId(@PathVariable Long id){
            pedido.deletarPeloId(id);
                return ResponseEntity.noContent().build();
    }

}
