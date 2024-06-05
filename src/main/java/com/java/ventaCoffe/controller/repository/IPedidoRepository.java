package com.java.ventaCoffe.controller.repository;

import com.java.ventaCoffe.model.entity.Pedido;
import com.java.ventaCoffe.model.entity.PedidoDto;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPedidoRepository extends JpaRepository<Pedido,Integer> {

    @Query(value = "SELECT SUM(totalPedido) FROM PEDIDO WHERE DAY(PEDIDO.fechaPedido) = DAY(current_date())",nativeQuery = true)
    Double totalVentasDelDia();

    @Query(value = "SELECT SUM(totalPedido) FROM PEDIDO",nativeQuery = true)
    Double totalVentas();

    @Query(value = "SELECT DATE(p.fechaPedido) AS DIA, SUM(p.totalPedido) FROM PEDIDO p GROUP BY DIA ORDER BY DIA",nativeQuery = true)
    List<Pedido> getPedido();

}
