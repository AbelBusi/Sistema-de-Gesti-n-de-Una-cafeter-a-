package com.java.ventaCoffe.controller.service;

import com.java.ventaCoffe.model.entity.Pedido;
import com.java.ventaCoffe.model.entity.PedidoDto;
import jakarta.persistence.Tuple;

import java.util.List;

public interface IPedidoService {

    Pedido guardarPedido(Pedido pedido);

    Double ventaDelDia();

    Double totalVentasPedidos();

    List<Pedido> findAll();

    List<Pedido> pedidoDia();

}
