package com.desafiopedidos.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "tb_pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    private String pedido;


    private Boolean confirmado;

    private Pedido(){}

    public Boolean getConfirmado() {
        return confirmado;
    }

    public void setConfirmado(Boolean confirmado) {
        this.confirmado = confirmado;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido(Long id, String pedido, Boolean confirmado) {
        this.id = id;
        this.pedido = pedido;
        this.confirmado = confirmado;
    }
}
