package com.java.ventaCoffe.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

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

    @Column(name = "ImagenProducto")
    private String imagenProducto;

    @Column
    private LocalDateTime fechaProducto;

    @ManyToOne
    private Usuario usuario;


    public Producto(Integer idProducto, String nombreProducto,double precioProducto, String imagenProducto){

        this.idProducto=idProducto;
        this.nombreProducto=nombreProducto;
        this.precioProducto=precioProducto;
        this.imagenProducto=imagenProducto;

    }

}
