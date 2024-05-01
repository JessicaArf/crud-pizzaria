package com.jessicaarf.crudpizzaria.repositories;

import com.jessicaarf.crudpizzaria.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
