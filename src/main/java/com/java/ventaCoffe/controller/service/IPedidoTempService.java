package com.java.ventaCoffe.controller.service;

import com.java.ventaCoffe.model.entity.PedidoTemporal;

import java.util.List;

public interface IPedidoTempService {

    List<PedidoTemporal> mostrarPedidos();

    PedidoTemporal guardarPedidoTemp(PedidoTemporal pedidoTemporal);

    void  eliminarRegistroTablaPedido();

    void eliminarPedidoId(Integer id);

}
