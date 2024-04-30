package com.jessicaarf.crudpizzaria.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRespostaDTO {

    private Long id;
    private String nome;
    private String telefone;
    private String endereco;
    private String login;

}
