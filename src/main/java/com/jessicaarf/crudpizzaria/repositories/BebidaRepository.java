package com.jessicaarf.crudpizzaria.repositories;

import com.jessicaarf.crudpizzaria.model.Bebida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BebidaRepository extends JpaRepository<Bebida, Long> {
    Optional<Bebida> findByNome(String nome);
}
