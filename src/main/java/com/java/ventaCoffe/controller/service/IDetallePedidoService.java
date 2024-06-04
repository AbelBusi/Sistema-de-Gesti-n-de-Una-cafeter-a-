package com.java.ventaCoffe.controller.service;

import com.java.ventaCoffe.model.entity.DetallePedido;
import com.java.ventaCoffe.model.entity.Pedido;

import java.util.List;

public interface IDetallePedidoService {

    List<DetallePedido> mostrarPedidos();

    DetallePedido guardarPedido(DetallePedido detallePedidos);

}
