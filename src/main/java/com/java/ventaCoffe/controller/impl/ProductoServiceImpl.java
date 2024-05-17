package com.java.ventaCoffe.controller.impl;

import com.java.ventaCoffe.controller.repository.IProductoRepository;
import com.java.ventaCoffe.controller.service.IProductoService;
import com.java.ventaCoffe.model.dto.ProductoDto;
import com.java.ventaCoffe.model.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
                .imagenProducto(productoDto.getImagenProducto())
                .usuario(productoDto.getUsuario())
                .build();
        return productoRepository.save(producto);
    }

    @Transactional
    @Override
    public Optional<Producto> findByNombreProducto(String nombreProducto) {
        return productoRepository.findByNombreProducto(nombreProducto);
    }

    @Transactional
    @Override
    public List<Producto> mostrarProducto() {
        return productoRepository.findAll();
    }

    @Transactional
    @Override
    public Optional<Producto> findById(Integer idProducto) {
        return productoRepository.findById(idProducto);
    }

    @Transactional
    @Override
    public Producto actualizarProducto(Producto producto){
        return productoRepository.save(producto);
    }

}
