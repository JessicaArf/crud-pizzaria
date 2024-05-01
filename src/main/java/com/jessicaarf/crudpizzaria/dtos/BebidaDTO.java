package com.jessicaarf.crudpizzaria.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class BebidaDTO {
    private Long idBebida;
    @NotNull
    private String nome;
    @NotNull
    private float preco;
}
