package com.java.ventaCoffe.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;

    @Column(length = 20)
    private String nombreProducto;

    @Column(length = 30)
    private String tipoProducto;

    @Column(length = 1000)
    private int stockProducto;

    @Column
    private double precioProducto;

    @Column(length = 20)
    private String estadoProducto;

    @Column
    private LocalDateTime fechaProducto;

    @ManyToOne
    private Usuario usuario;

}
