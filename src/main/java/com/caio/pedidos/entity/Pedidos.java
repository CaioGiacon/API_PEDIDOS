package com.caio.pedidos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="pedidos")
public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "cliente", length = 150)
    private String cliente;
    @Column(name = "produto", length = 100)
    private String produto;
    @Column(name = "quantidade")
    private int quantidade;
    @Column(name = "valor-unitario")
    private int valorUnitario;
    @Column(name = "valor-total")
    private int valorTotal;
    @Column(name = "status")
    private String status;
    @CreationTimestamp
    @Column(name = "data-criacao", updatable = false)
    private LocalDate dataCriacao;

}
