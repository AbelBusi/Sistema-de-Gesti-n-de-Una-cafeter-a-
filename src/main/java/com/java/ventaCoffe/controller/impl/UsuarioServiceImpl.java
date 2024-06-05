package com.java.ventaCoffe.controller.impl;

import com.java.ventaCoffe.controller.repository.IUsuarioRepository;
import com.java.ventaCoffe.controller.service.IUsuarioService;
import com.java.ventaCoffe.model.dto.UsuarioDto;
import com.java.ventaCoffe.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


//Clase que ejecutara todas las consultas jpa
@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Transactional
    @Override
    public Optional<Usuario> idUsuario(Integer id) {


        return usuarioRepository.findById(id);
    }

    @Transactional
    @Override
    public Integer totalDeUsuarios() {
        return usuarioRepository.totalUsuarios();
    }


    //Servicio del correo si es que existe
    @Transactional
    @Override
    public Optional<Usuario> findByCorreoUsuario(String correoUsuario) {
        return usuarioRepository.findByCorreoUsuario(correoUsuario);
    }

    @Transactional
    @Override
    public Usuario guardarUsuario(UsuarioDto usuarioDto) {

        Usuario usuario = Usuario.builder()
                .correoUsuario(usuarioDto.getCorreoUsuarioDto())
                .claveUsuario(usuarioDto.getClaveUsuarioDto())
                .preguntaUsuario(usuarioDto.getPreguntaUsuarioDto())
                .resptValidacionUsuario(usuarioDto.getResptValidacionUsuarioDto())
                .build();

        return usuarioRepository.save(usuario);
    }

    @Transactional
    @Override
    public Usuario actualizarClave(Usuario usuario) {

        return usuarioRepository.save(usuario);
    }


}
