package com.java.ventaCoffe.controller.service;

import com.java.ventaCoffe.model.dto.UsuarioDto;
import com.java.ventaCoffe.model.entity.Usuario;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.Optional;

public interface IUsuarioService {

    //Traen el email y clave de la clase usuario, serviran para validar si existen los usuarios o no
    Optional<Usuario> findByCorreoUsuario(String correoUsuario);

    Optional<Usuario> findByClaveUsuario(String claveUsuario);

    //Validaciones con respecto a las preguntas
    Optional<Usuario> findByPreguntaUsuario(String preguntaUsuario);

    Optional<Usuario> findByResptValidacionUsuario(String respuestaUsuario);

    Usuario guardarUsuario (UsuarioDto usuarioDto);

    Optional<Usuario> idUsuario (Integer id);


}
