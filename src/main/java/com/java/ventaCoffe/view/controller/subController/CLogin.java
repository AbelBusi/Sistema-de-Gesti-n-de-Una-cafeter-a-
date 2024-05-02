package com.java.ventaCoffe.view.controller.subController;

import com.java.ventaCoffe.controller.impl.UsuarioServiceImpl;
import com.java.ventaCoffe.model.dto.UsuarioDto;
import com.java.ventaCoffe.model.entity.Usuario;
import com.java.ventaCoffe.view.controller.ControllerLogin;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
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

        if(!correo.isEmpty() && !clave.isEmpty()){

        if (userCorreo.isPresent() && userClave.isPresent()) {

            loggger.info("Usuario {}",userClave);

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText(null);
            alerta.setTitle("Prueba ");
            alerta.setContentText(" correo: "+ correo + " Clave: "+clave);
            alerta.showAndWait();

        } else {

            loggger.info("El usuario no existe");
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Prueba ");
            alerta.setContentText("Usuario no registrado en la BD");
            alerta.showAndWait();

        }
        }
        else {

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Autenticacion del usuario");
            alerta.setContentText("Error: Correo o clave vacios");
            alerta.showAndWait();


        }

    }

    public void Registrarse(TextField correoUsuario, PasswordField claveUsuaio, ComboBox pregunta, TextField respuestaUsuario){
        UsuarioDto usuarioDto = new UsuarioDto();
        Usuario usuario = new Usuario();

        usuarioDto.setCorreoUsuarioDto(correoUsuario.getText());
        usuarioDto.setClaveUsuarioDto(claveUsuaio.getText());
        usuarioDto.setPreguntaUsuarioDto(String.valueOf(pregunta.getSelectionModel().getSelectedItem()));
        usuarioDto.setResptValidacionUsuarioDto(respuestaUsuario.getText());
        usuario= usuarioService.guardarUsuario(usuarioDto);

    }

}
