package com.java.ventaCoffe.controller.repository;

import com.java.ventaCoffe.model.entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends CrudRepository<Usuario,Integer> {




}
