package com.java.ventaCoffe.controller.impl;

import com.java.ventaCoffe.controller.repository.IDetallePedidoRepository;
import com.java.ventaCoffe.controller.service.IDetallePedidoService;
import com.java.ventaCoffe.model.entity.DetallePedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DetallePedidoServiceImpl implements IDetallePedidoService {

    @Autowired
    private IDetallePedidoRepository pedidoRepository;


    @Transactional
    @Override
    public List<DetallePedido> mostrarPedidos() {
        return pedidoRepository.findAll();
    }

    @Transactional
    @Override
    public DetallePedido guardarPedido(DetallePedido detallePedidos) {
        return pedidoRepository.save(detallePedidos);
    }
}
