package com.java.ventaCoffe.controller.repository;

import com.java.ventaCoffe.model.entity.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IDetallePedidoRepository extends JpaRepository<DetallePedido,Integer> {

    @Query(value = "SELECT SUM(CantidadDetallePedido) FROM DETALLEPEDIDO",nativeQuery = true)
    Integer totalProductosVendidos();
}
