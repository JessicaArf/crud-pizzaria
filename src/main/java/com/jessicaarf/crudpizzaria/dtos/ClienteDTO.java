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

        @NotNull(message = "O campo nome não pode ser nulo.")
        private String nome;

        @NotNull(message = "O campo telefone não pode ser nulo.")
        private String telefone;

        @NotNull(message = "O campo endereco não pode ser nulo.")
        private String endereco;

        @NotNull(message = "O campo login não pode ser nulo.")
        private String login;

        @NotNull(message = "O campo senha não pode ser nulo.")
        private String senha;

}
