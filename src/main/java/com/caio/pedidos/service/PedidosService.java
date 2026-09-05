package com.caio.pedidos.service;

import com.caio.pedidos.entity.Pedidos;
import com.caio.pedidos.exceptions.ResourceNotFoundException;
import com.caio.pedidos.repository.PedidosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidosService {

    private final PedidosRepository pedidosRepository;

    public Pedidos salvarPedido(Pedidos pedido) {
        calcularValorTotal(pedido);

        if (pedido.getStatus() == null || pedido.getStatus().isBlank()) {
            pedido.setStatus("CRIADO");
        }
        return pedidosRepository.save(pedido);
    }

    public Pedidos buscarPorId(Long id) {
        return pedidosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado com o ID: " + id));
    }

    public List<Pedidos> listarPedido() {
        return pedidosRepository.findAll();
    }

    public Pedidos atualizarStatus(Long id, String novoStatus) {

        Pedidos pedidoExistente = pedidosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido inexistente"));

        pedidoExistente.setStatus(novoStatus);
        return pedidosRepository.save(pedidoExistente);
    }

    public void deletarPedido(Long id) {
        if (!pedidosRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pedido não encontrado com o ID: " + id);
        }
        pedidosRepository.deleteById(id);
    }

    private void calcularValorTotal(Pedidos pedido) {
        double total = (double) pedido.getQuantidade() * pedido.getValorUnitario();
        pedido.setValorTotal(total);
    }
}