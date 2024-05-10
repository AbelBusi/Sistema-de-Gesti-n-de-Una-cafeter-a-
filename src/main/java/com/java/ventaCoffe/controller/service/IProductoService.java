package com.java.ventaCoffe.controller.service;

import com.java.ventaCoffe.model.dto.ProductoDto;
import com.java.ventaCoffe.model.entity.Producto;

import java.util.Optional;

public interface IProductoService {

    Producto guardarProducto (ProductoDto productoDto);

    //Verifica si ya existe otro producto con el mismo nombre
    Optional<Producto> findByNombreProducto(String nombreProducto);

}
