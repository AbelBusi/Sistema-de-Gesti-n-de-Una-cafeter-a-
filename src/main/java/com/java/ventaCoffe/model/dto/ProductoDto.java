package com.java.ventaCoffe.model.dto;

import com.java.ventaCoffe.model.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDto {

    private String nombreProducto;

    private String tipoProducto;

    private int stockProducto;

    private double precioProducto;

    private String estadoProducto;

    private Date fechaProducto;

    private Usuario usuario;

}
