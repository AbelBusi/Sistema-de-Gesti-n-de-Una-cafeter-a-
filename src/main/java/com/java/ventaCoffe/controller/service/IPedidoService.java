package com.java.ventaCoffe.controller.service;

import com.java.ventaCoffe.model.entity.Pedido;

import java.util.List;

public interface IPedidoService {

    Pedido guardarPedido(Pedido pedido);

    List<Pedido> findAll();

}
