package com.java.ventaCoffe.controller.impl;

import com.java.ventaCoffe.controller.repository.IPedidoRepository;
import com.java.ventaCoffe.controller.service.IPedidoService;
import com.java.ventaCoffe.model.dto.PedidoDtoProjection;
import com.java.ventaCoffe.model.entity.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServiceImpl implements IPedidoService {

    @Autowired
    private IPedidoRepository pedidoRepository;

    @Transactional
    @Override
    public Pedido guardarPedido(Pedido pedido) {
        try {
            return pedidoRepository.save(pedido);
        } catch (Exception e) {
            System.out.println("Error con la bd: " + e.getMessage());
            return pedido;
        }
    }

    @Transactional
    @Override
    public Double ventaDelDia() {
        return pedidoRepository.totalVentasDelDia();
    }

    @Transactional
    @Override
    public Double totalVentasPedidos() {
        return pedidoRepository.totalVentas();
    }

    @Transactional
    @Override
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @Transactional
    @Override
    public List<PedidoDtoProjection> pedidoDia() {
        return pedidoRepository.getPedido();
    }

    public String generarNumeroPedido() {

        int numero = 0;

        String numeroConcatenado = "";

        List<Pedido> pedidos = findAll();

        List<Integer> numeros = new ArrayList<Integer>();

        pedidos.stream().forEach(p -> numeros.add(Integer.parseInt(p.getNumeroPedido())));

        //Validacion
        if (pedidos.isEmpty()) {

            numero = 1;

        } else {

            numero = numeros.stream().max(Integer::compare).get();
            numero++;

        }

        //Se introduce el formato 000000000
        if (numero < 10) {

            numeroConcatenado = "000000000" + String.valueOf(numero);

        } else if (numero < 100) {

            numeroConcatenado = "00000000" + String.valueOf(numero);

        } else if (numero < 1000) {

            numeroConcatenado = "0000000" + String.valueOf(numero);

        } else if (numero < 10000) {

            numeroConcatenado = "000000" + String.valueOf(numero);

        }

        return numeroConcatenado;

    }

}
