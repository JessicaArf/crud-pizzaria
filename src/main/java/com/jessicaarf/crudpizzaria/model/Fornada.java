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
public class Fornada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFornada;
    private Integer numFornada;
    private Integer qtdPizzas;

}
