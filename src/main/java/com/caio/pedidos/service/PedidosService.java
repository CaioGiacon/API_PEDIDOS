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

    public Pedidos atualizarPedido(Long id, Pedidos pedidoAtualizado) {
        return pedidosRepository.findById(id).map(pedidoExistente -> {
            pedidoExistente.setCliente(pedidoAtualizado.getCliente());
            pedidoExistente.setProduto(pedidoAtualizado.getProduto());
            pedidoExistente.setQuantidade(pedidoAtualizado.getQuantidade());
            pedidoExistente.setValorUnitario(pedidoAtualizado.getValorUnitario());
            pedidoExistente.setStatus(pedidoAtualizado.getStatus());

            calcularValorTotal(pedidoExistente);

            return pedidosRepository.save(pedidoExistente);
        }).orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado com o ID: " + id));
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