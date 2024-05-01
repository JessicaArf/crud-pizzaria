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
public class ClienteDTO {

        private Long id;

        @NotNull(message = "Nome não pode ser nulo.")
        private String nome;

        @NotNull(message = "Telefone não pode ser nulo.")
        private String telefone;

        @NotNull(message = "Endereco não pode ser nulo.")
        private String endereco;

        @NotNull(message = "Login não pode ser nulo.")
        private String login;

        @NotNull(message = "Senha não pode ser nulo.")
        private String senha;

}
