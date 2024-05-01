package com.jessicaarf.crudpizzaria.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FornadaDTO {

    private Long idFornada;
    @NotNull
    private int numFornada;
    @NotNull
    private int qtdPizzas;

}
