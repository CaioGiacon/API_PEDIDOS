package com.caio.pedidos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PedidosResponseDTO(
        Long id,
        String cliente,
        String produto,
        int quantidade,

        @JsonProperty("valor_unitario")
        int valorUnitario,

        @JsonProperty("valor_total")
        double valorTotal,

        String status
) {}
