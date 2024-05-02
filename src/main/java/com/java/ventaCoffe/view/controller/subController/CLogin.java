package com.java.ventaCoffe.view.controller.subController;

import com.java.ventaCoffe.controller.impl.UsuarioServiceImpl;
import com.java.ventaCoffe.model.entity.Usuario;
import com.java.ventaCoffe.view.controller.ControllerLogin;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CLogin {

    private final Logger loggger = LoggerFactory.getLogger(CLogin.class);

    @Autowired
    private UsuarioServiceImpl usuarioService;


    public void Ingresar(TextField correoUsuario, PasswordField claveUsuario){

        String correo = correoUsuario.getText();
        String clave = claveUsuario.getText();
        loggger.info("Clave del usuario {}",clave);
        Optional<Usuario> userCorreo=usuarioService.findByCorreoUsuario(correo);
        Optional<Usuario> userClave=usuarioService.findByClaveUsuario(clave);

        //loggger.info("Usuario de db: {}",user.get());

        if (userCorreo.isPresent() && userClave.isPresent()) {

            loggger.info("Usuario {}",userClave);


        } else {

            loggger.info("El usuario no existe");

        }

    }

}
