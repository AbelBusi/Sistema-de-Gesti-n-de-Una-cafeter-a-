package com.java.ventaCoffe.controller.service;

import com.java.ventaCoffe.model.dto.ProductoDto;
import com.java.ventaCoffe.model.entity.Producto;

public interface IProductoService {

    Producto guardarProducto (ProductoDto productoDto);

}
