package com.java.ventaCoffe.view.controller.login;

import com.java.ventaCoffe.controller.impl.UsuarioServiceImpl;
import com.java.ventaCoffe.model.dto.UsuarioDto;
import com.java.ventaCoffe.model.entity.Usuario;
import jakarta.persistence.criteria.CriteriaBuilder;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecuperarClaveController {

    private final Logger loggger = LoggerFactory.getLogger(RecuperarClaveController.class);

    @Autowired
    private UsuarioServiceImpl usuarioService;

    public void limpiar(PasswordField clave, PasswordField claveValidacion){

        clave.setText("");
        claveValidacion.setText("");

    }

    public void limpiarPreguntas(TextField correo, TextField respuesta){

        correo.setText("");
        respuesta.setText("");

    }

    public void VerificarCorreoExistente(TextField correoUsuario,
                                         ComboBox preguntaUsuario,
                                         TextField respuestaUsuario,
                                         AnchorPane paneContinuar) {

        String correo = correoUsuario.getText();
        String pregunta = (String) preguntaUsuario.getSelectionModel().getSelectedItem();
        String respuesta = respuestaUsuario.getText();
        //String clave = claveUsuario.getText();

        Optional<Usuario> userCorreo = usuarioService.findByCorreoUsuario(correo);

        if (!correo.isEmpty() && !(pregunta == null) && !respuesta.isEmpty()) {

            if (userCorreo.isPresent()) {

                if (userCorreo.get().getPreguntaUsuario().equalsIgnoreCase(pregunta)
                        && userCorreo.get().getResptValidacionUsuario().equalsIgnoreCase(respuesta)) {

                    loggger.info("Si existe el correo {}", userCorreo);
                    paneContinuar.setVisible(true);
                    userCorreo.get().setClaveUsuario("HOLA");
                    usuarioService.actualizarClave(userCorreo.orElse(null));

                } else {

                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setHeaderText(null);
                    alerta.setTitle("Error de verificacion");
                    alerta.setContentText("La pregunta y el correo deben ser las correctas con respecto al correo");
                    alerta.showAndWait();


                }

            } else {

                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setHeaderText(null);
                alerta.setTitle("Error de verificacion");
                alerta.setContentText("No existe un usuario con el correo ingresado");
                alerta.showAndWait();

            }

        } else {

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Error de verificacion");
            alerta.setContentText("Las casillas no deben estar vacias, Seleccione una pregunta :)");
            alerta.showAndWait();

        }

    }

    public void cambiarClaveUsuario(TextField correoUsuario,
                                    TextField respuesta,
                                    PasswordField claveUsuario,
                                    PasswordField validacionClave,
                                    AnchorPane cambiarClave,
                                    AnchorPane verificarPregunta) {
        String correo = correoUsuario.getText();
        String clave = claveUsuario.getText();
        String valClave = validacionClave.getText();
        Optional<Usuario> usuario = usuarioService.findByCorreoUsuario(correo);
        if (!clave.isEmpty() && !valClave.isEmpty()) {
            if (clave.equalsIgnoreCase(valClave)) {
                usuario.get().setClaveUsuario(clave);
                usuarioService.actualizarClave(usuario.orElse(null));
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setHeaderText(null);
                alerta.setTitle("Cambio completo");
                alerta.setContentText("La clave del usuario se ha cambiado correctamente");
                alerta.showAndWait();
                limpiar(claveUsuario,validacionClave);
                limpiarPreguntas(correoUsuario,respuesta);
                cambiarClave.setVisible(false);
                verificarPregunta.setVisible(false);
            } else {

                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setHeaderText(null);
                alerta.setTitle("Error de validacion");
                alerta.setContentText("Las claves deben coincidir");
                alerta.showAndWait();
                limpiar(claveUsuario,validacionClave);

            }

        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Error de validacion");
            alerta.setContentText("Las casillas no deben estar vacias");
            alerta.showAndWait();
            limpiar(claveUsuario,validacionClave);

        }

    }

}
