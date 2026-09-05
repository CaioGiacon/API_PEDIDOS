package com.caio.pedidos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PedidosRequestDTO (
        String cliente,
        String produto,
        int quantidade,

        @JsonProperty("valor_unitario")
        int valorUnitario
){}
