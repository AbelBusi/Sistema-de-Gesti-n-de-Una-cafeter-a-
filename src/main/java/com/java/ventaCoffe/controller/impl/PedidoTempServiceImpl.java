package com.java.ventaCoffe.controller.impl;

import com.java.ventaCoffe.controller.repository.IPedidoTempRepository;
import com.java.ventaCoffe.controller.service.IPedidoTempService;
import com.java.ventaCoffe.model.entity.PedidoTemporal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoTempServiceImpl implements IPedidoTempService {

    @Autowired
    private IPedidoTempRepository tempRepository;

    @Transactional
    @Override
    public List<PedidoTemporal> mostrarPedidos() {
        return tempRepository.findAll();
    }

    @Transactional
    @Override
    public PedidoTemporal guardarPedidoTemp(PedidoTemporal pedidoTemporal) {
        return tempRepository.save(pedidoTemporal);
    }


    @Override
    public void eliminarRegistroTablaPedido() {

        tempRepository.deleteAll();
    }
}
