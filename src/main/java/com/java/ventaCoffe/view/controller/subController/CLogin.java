package com.java.ventaCoffe.view.controller.subController;

import com.java.ventaCoffe.controller.impl.UsuarioServiceImpl;
import com.java.ventaCoffe.model.dto.UsuarioDto;
import com.java.ventaCoffe.model.entity.Usuario;
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

    public void limpiarRegistro(TextField correo, PasswordField clave, TextField respuesta) {

        correo.setText("");
        clave.setText("");
        respuesta.setText("");


    }

    public void limpiarIngreso(TextField correo, PasswordField clave) {
        correo.setText("");
        clave.setText("");
    }

    public void Ingresar(TextField correoUsuario, PasswordField claveUsuario) {

        String correo = correoUsuario.getText();
        String clave = claveUsuario.getText();
        loggger.info("Clave del usuario {}", clave);
        Optional<Usuario> userCorreo = usuarioService.findByCorreoUsuario(correo);
        //Optional<Usuario> userClave=usuarioService.findByClaveUsuario(clave);

        //loggger.info("Usuario de db: {}",user.get());

        if (!correo.isEmpty() && !clave.isEmpty()) {

            if (userCorreo.isPresent()) {

                if (userCorreo.get().getClaveUsuario().equalsIgnoreCase(clave)) {
                    loggger.info("Usuario {}", clave);

                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setHeaderText(null);
                    alerta.setTitle("Prueba ");
                    alerta.setContentText(" correo: " + correo + " Clave: " + clave);
                    limpiarIngreso(correoUsuario, claveUsuario);
                    alerta.showAndWait();
                } else {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setHeaderText(null);
                    alerta.setTitle("Autenticacion ");
                    alerta.setContentText("La clave del usuario no es valida");
                    limpiarIngreso(correoUsuario, claveUsuario);
                    alerta.showAndWait();

                }

            } else {

                loggger.info("El usuario no existe");
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setHeaderText(null);
                alerta.setTitle("Prueba ");
                alerta.setContentText("Usuario no registrado en la BD");
                limpiarIngreso(correoUsuario, claveUsuario);
                alerta.showAndWait();

            }

        } else {

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Autenticacion del usuario");
            alerta.setContentText("Error: Correo o clave vacios");
            limpiarIngreso(correoUsuario, claveUsuario);
            alerta.showAndWait();
        }
    }

    public void Registrarse(TextField correoUsuario, PasswordField claveUsuaio, ComboBox pregunta, TextField respuestaUsuario) {

        UsuarioDto usuarioDto = new UsuarioDto();
        Usuario usuario = new Usuario();

        String correo = correoUsuario.getText();
        String clave = claveUsuaio.getText();
        String preguntaSeleccionada = (String) pregunta.getSelectionModel().getSelectedItem();
        String respuesta = respuestaUsuario.getText();
        Optional<Usuario> user = usuarioService.findByCorreoUsuario(correo);

        if (!correo.isEmpty() && !clave.isEmpty() && !(preguntaSeleccionada == null)
                && !respuesta.isEmpty()) {

            if (!user.isPresent()) {

                usuarioDto.setCorreoUsuarioDto(correo);
                usuarioDto.setClaveUsuarioDto(clave);
                usuarioDto.setPreguntaUsuarioDto(preguntaSeleccionada);
                usuarioDto.setResptValidacionUsuarioDto(respuesta);
                usuario = usuarioService.guardarUsuario(usuarioDto);

                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setHeaderText(null);
                alerta.setTitle("Registro Correcto");
                alerta.setContentText("El usuario fue registrado con exito, puede ingresar :)");
                alerta.showAndWait();

                limpiarRegistro(correoUsuario, claveUsuaio, respuestaUsuario);

            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setHeaderText(null);
                alerta.setTitle("Error de autenticacion");
                alerta.setContentText("El correo que ingreso ya existe, Intente con otro");
                alerta.showAndWait();
                limpiarRegistro(correoUsuario, claveUsuaio, respuestaUsuario);
            }

        } else {

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Error de autenticacion");
            alerta.setContentText("Las casillas no pueden estar vacias, " +
                    "seleccione una pregunta e Intentalo denuevo :)");
            alerta.showAndWait();
            limpiarRegistro(correoUsuario, claveUsuaio, respuestaUsuario);

        }
    }

}
