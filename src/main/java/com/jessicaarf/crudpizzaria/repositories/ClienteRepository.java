package com.jessicaarf.crudpizzaria.repositories;

import com.jessicaarf.crudpizzaria.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}
