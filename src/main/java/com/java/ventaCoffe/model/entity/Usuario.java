package com.java.ventaCoffe.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Basic
    @Column(length = 50)
    private String correoUsuario;

    @Column(length = 30)
    private String claveUsuario;

    @Column(length = 100)
    private String preguntaUsuario;

    @Column(length = 60)
    private String resptValidacionUsuario;

}
