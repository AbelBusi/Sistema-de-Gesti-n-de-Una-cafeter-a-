package com.java.ventaCoffe.controller.repository;

import com.java.ventaCoffe.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario,Integer> {

    Optional<Usuario> findByCorreoUsuario(String correoUsuario);

    @Query(value = "SELECT count(idUsuario) FROM USUARIO",nativeQuery = true)
    Integer totalUsuarios();


}
