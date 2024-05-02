package com.jessicaarf.crudpizzaria.dtos;

import com.jessicaarf.crudpizzaria.model.Ingrediente;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PizzaDTO {

    private int idPizza;
    @NotNull(message = "Nome não pode ser nulo")
    private String nome;
    @NotNull(message = "PrecoBase não pode ser nulo.")
    private float precoBase;
    @NotNull(message = "Personalizada não pode ser nulo.")
    private boolean personalizada;
    @NotNull(message = "Ingrediente não pode ser nulo.")
    private List<Ingrediente> ingredientes;

}