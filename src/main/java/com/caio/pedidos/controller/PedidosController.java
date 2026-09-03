package com.caio.pedidos.controller;

import com.caio.pedidos.dto.PedidosRequestDTO;
import com.caio.pedidos.entity.Pedidos;
import com.caio.pedidos.service.PedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

    @Autowired
    private PedidosService pedidosService;

    @PostMapping
    public ResponseEntity<Pedidos> criarPedido(@RequestBody PedidosRequestDTO dto) {

        Pedidos novoPedido = new Pedidos();
        novoPedido.setCliente(dto.cliente());
        novoPedido.setProduto(dto.produto());
        novoPedido.setQuantidade(dto.quantidade());

        novoPedido.setValorUnitario(dto.valorUnitario());

        Pedidos pedidoCriado = pedidosService.salvarPedido(novoPedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoCriado);
    }
}
