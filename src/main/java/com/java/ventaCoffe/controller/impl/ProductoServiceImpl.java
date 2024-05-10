package com.java.ventaCoffe.controller.impl;

import com.java.ventaCoffe.controller.repository.IProductoRepository;
import com.java.ventaCoffe.controller.service.IProductoService;
import com.java.ventaCoffe.model.dto.ProductoDto;
import com.java.ventaCoffe.model.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private IProductoRepository productoRepository;

    @Transactional
    @Override
    public Producto guardarProducto(ProductoDto productoDto) {

        Producto producto = Producto.builder()
                .nombreProducto(productoDto.getNombreProducto())
                .precioProducto(productoDto.getPrecioProducto())
                .stockProducto(productoDto.getStockProducto())
                .tipoProducto(productoDto.getTipoProducto())
                .estadoProducto(productoDto.getEstadoProducto())
                .fechaProducto(productoDto.getFechaProducto())
                .usuario(productoDto.getUsuario())
                .build();
        return productoRepository.save(producto);
    }

    @Override
    public Optional<Producto> findByNombreProducto(String nombreProducto) {
        return productoRepository.findByNombreProducto(nombreProducto);
    }
}
