package com.jessicaarf.crudpizzaria.repositories;

import com.jessicaarf.crudpizzaria.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    Optional<Cliente> findByLogin(String login);
}
