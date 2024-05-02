package com.java.ventaCoffe.controller.impl;

import com.java.ventaCoffe.controller.repository.IUsuarioRepository;
import com.java.ventaCoffe.controller.service.IUsuarioService;
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

    @Override
    public Optional<Usuario> idUsuario(Integer id) {
        return Optional.empty();
    }


    //Servicio del correo si es que existe
    //@Transactional
    @Override
    public Optional<Usuario> findByCorreoUsuario(String correoUsuario) {
        return usuarioRepository.findByCorreoUsuario(correoUsuario);
    }

    //Servicio del correo si es que existe
    //@Transactional
    @Override
    public Optional<Usuario> findByClaveUsuario(String claveUsuario) {
        return usuarioRepository.findByClaveUsuario(claveUsuario);
    }


}
