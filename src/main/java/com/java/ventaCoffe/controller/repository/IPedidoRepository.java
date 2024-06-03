package com.java.ventaCoffe.controller.repository;

import com.java.ventaCoffe.model.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPedidoRepository extends JpaRepository<Pedido,Integer> {
}
