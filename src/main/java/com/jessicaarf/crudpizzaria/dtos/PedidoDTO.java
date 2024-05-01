package com.jessicaarf.crudpizzaria.dtos;

import com.jessicaarf.crudpizzaria.model.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    private Long idPedido;
    @NotNull(message = "Cliente não pode ser nulo.")
    Cliente cliente;
    @NotNull(message = "Fornada não pode ser nulo.")
    Fornada fornada;
    @NotNull(message = "Pizza não pode ser nulo.")
    private List<PizzaPedida> pizzaPedidos;
    private List<BebidaPedida> bebidaPedidos;

}
