package com.java.ventaCoffe.model.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Producto {

    private Integer idProducto;

    private String nombreProducto;
    private String tipoProducto;
    private int stockProducto;
    private double precioProducto;
    private String estadoProducto;
    private Date fechaProducto;

}
