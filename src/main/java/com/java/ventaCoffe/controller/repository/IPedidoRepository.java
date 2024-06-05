package com.java.ventaCoffe.controller.repository;

import com.java.ventaCoffe.model.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPedidoRepository extends JpaRepository<Pedido,Integer> {

    @Query(value = "SELECT SUM(totalPedido) FROM PEDIDO WHERE DAY(PEDIDO.fechaPedido) = DAY(current_date())",nativeQuery = true)
    Double totalVentasDelDia();

    @Query(value = "SELECT SUM(totalPedido) FROM PEDIDO",nativeQuery = true)
    Double totalVentas();

}
