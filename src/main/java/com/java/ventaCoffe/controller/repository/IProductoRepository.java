package com.java.ventaCoffe.controller.repository;

import com.java.ventaCoffe.model.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductoRepository extends JpaRepository<Producto,Integer> {

    Optional<Producto> findByNombreProducto(String nombreProducto);

}
