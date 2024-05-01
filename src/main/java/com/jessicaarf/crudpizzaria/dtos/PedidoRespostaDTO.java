package com.jessicaarf.crudpizzaria.dtos;

import com.jessicaarf.crudpizzaria.model.BebidaPedida;
import com.jessicaarf.crudpizzaria.model.Fornada;
import com.jessicaarf.crudpizzaria.model.PizzaPedida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRespostaDTO {

    private Long idPedido;
    ClienteRespostaDTO cliente;
    Fornada fornada;
    private List<PizzaPedida> pizzaPedidos;
    private List<BebidaPedida> bebidaPedidos;
}
