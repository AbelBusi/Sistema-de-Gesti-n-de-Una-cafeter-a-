package com.java.ventaCoffe.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetallePedido;

    private String nombreDetallePedido;
    private Integer cantidadDetallePedido;
    private double precioDetallePedido;
    private double totalDetallePedido;

    @ManyToOne
    private Pedido pedido;

}
