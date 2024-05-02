package com.java.ventaCoffe.controller.service;

import com.java.ventaCoffe.model.entity.Usuario;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.Optional;

public interface IUsuarioService {

    //Traen el email y clave de la clase usuario, serviran para validar si existen los usuarios o no
    Optional<Usuario> findByCorreoUsuario(String correoUsuario);

    Optional<Usuario> findByClaveUsuario(String claveUsuario);

    Optional<Usuario> idUsuario (Integer id);


}
