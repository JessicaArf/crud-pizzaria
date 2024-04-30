package com.jessicaarf.crudpizzaria.repositories;

import com.jessicaarf.crudpizzaria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    Optional<Pizza> findByNome(String nome);
}
