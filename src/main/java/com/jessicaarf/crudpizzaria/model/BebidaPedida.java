package com.jessicaarf.crudpizzaria.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "TB_BEBIDA_PEDIDA")
public class BebidaPedida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBebidaPedida;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pedido")
    @JsonBackReference
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_bebida")
    private Bebida bebida;

    private int quantidade;

}
