package com.java.ventaCoffe.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(length = 50)
    private String correoUsuario;

    @Column(length = 30)
    private String claveUsuario;

    @Column(length = 100)
    private String preguntaUsuario;

    @Column(length = 60)
    private String resptValidacionUsuario;

    public Usuario(Integer idUsuario,
                   String correoUsuario,
                   String claveUsuario,
                   String preguntaUsuario,
                   String resptValidacionUsuario) {

        this.idUsuario = idUsuario;
        this.correoUsuario = correoUsuario;
        this.claveUsuario = claveUsuario;
        this.preguntaUsuario = preguntaUsuario;
        this.resptValidacionUsuario = resptValidacionUsuario;
    }

    @OneToMany(mappedBy = "usuario")
    private List<Producto> productos;

}
