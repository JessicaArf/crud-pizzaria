package com.jessicaarf.crudpizzaria.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BebidaDTO {
    private Long idBebida;
    @NotNull(message = "Nome não pode ser nulo.")
    private String nome;
    @NotNull(message = "Preco não pode ser nulo.")
    private float preco;
}
