package com.java.ventaCoffe.model.entity;


import org.springframework.stereotype.Service;

@Service
public class DataTransFerService {

    private String nombrePedido;
    private int cantidad;
    private double precioPedido;

    // Getters y Setters

    public String getNombrePedido() {
        return nombrePedido;
    }

    public void setNombrePedido(String nombrePedido) {
        this.nombrePedido = nombrePedido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioPedido() {
        return precioPedido;
    }

    public void setPrecioPedido(double precioPedido) {
        this.precioPedido = precioPedido;
    }
}


