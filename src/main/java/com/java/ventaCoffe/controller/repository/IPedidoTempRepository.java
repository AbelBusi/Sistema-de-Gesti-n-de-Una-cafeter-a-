package com.java.ventaCoffe.controller.repository;

import com.java.ventaCoffe.model.entity.PedidoTemporal;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface IPedidoTempRepository extends JpaRepository<PedidoTemporal,Integer> {

    @Query(value ="SELECT SUM(precioPedidoTemp) FROM PedidoTemporal"  ,nativeQuery = true)
    Double sumarTotalPedido();

}
