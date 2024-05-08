package com.java.ventaCoffe.controller.repository;

import com.java.ventaCoffe.model.entity.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends CrudRepository<Producto,Integer> {
}
