package com.desafiopedidos.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PedidoDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String pedido;

    private Boolean confirmado;

    public PedidoDTO() {
    }

    public PedidoDTO(String pedido, Boolean confirmado) {
        this.pedido = pedido;
        this.confirmado = confirmado;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public Boolean getConfirmado() {
        return confirmado;
    }

    public void setConfirmado(Boolean confirmado) {
        this.confirmado = confirmado;
    }
}
