package com.java.ventaCoffe.controller.repository;

import com.java.ventaCoffe.model.entity.PedidoTemporal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPedidoTempRepository extends JpaRepository<PedidoTemporal,Integer> {
}
