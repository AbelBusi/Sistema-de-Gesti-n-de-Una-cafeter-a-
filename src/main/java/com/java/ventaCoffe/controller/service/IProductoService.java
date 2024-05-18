package com.java.ventaCoffe.controller.service;

import com.java.ventaCoffe.model.dto.ProductoDto;
import com.java.ventaCoffe.model.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {

    Producto guardarProducto (ProductoDto productoDto);

    //Verifica si ya existe otro producto con el mismo nombre
    Optional<Producto> findByNombreProducto(String nombreProducto);

    List<Producto> mostrarProducto ();

    Optional<Producto> findById(Integer idProducto);

    Producto actualizarProducto(Producto producto);

    public void eliminarProducto(Integer idProducto);




}
