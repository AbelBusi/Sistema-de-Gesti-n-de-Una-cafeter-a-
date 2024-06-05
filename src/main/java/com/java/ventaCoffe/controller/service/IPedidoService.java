package com.java.ventaCoffe.controller.service;

import com.java.ventaCoffe.model.dto.PedidoDtoProjection;
import com.java.ventaCoffe.model.entity.Pedido;

import java.util.List;

public interface IPedidoService {

    Pedido guardarPedido(Pedido pedido);

    Double ventaDelDia();

    Double totalVentasPedidos();

    List<Pedido> findAll();

    List<PedidoDtoProjection> pedidoDia();

}
