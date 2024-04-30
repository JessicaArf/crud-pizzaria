package com.jessicaarf.crudpizzaria.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PizzaPedida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tamanho_id")
    private Tamanho tamanho;

    private int quantidade;

}
