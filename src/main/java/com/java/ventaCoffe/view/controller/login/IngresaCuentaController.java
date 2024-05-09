package com.java.ventaCoffe.view.controller.login;

import com.java.ventaCoffe.controller.impl.UsuarioServiceImpl;
import com.java.ventaCoffe.model.entity.Usuario;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngresaCuentaController {

    private final Logger loggger = LoggerFactory.getLogger(IngresaCuentaController.class);

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

    public String Ingresar(TextField correoUsuario, PasswordField claveUsuario) {

        String correo = correoUsuario.getText();
        String clave = claveUsuario.getText();
        loggger.info("Clave del usuario {}", clave);
        Optional<Usuario> userCorreo = usuarioService.findByCorreoUsuario(correo);

        if (!correo.isEmpty() && !clave.isEmpty()) {

            if (userCorreo.isPresent()) {

                if (userCorreo.get().getClaveUsuario().equalsIgnoreCase(clave)) {
                    loggger.info("Usuario {}", clave);

                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setHeaderText(null);
                    alerta.setTitle("Prueba ");
                    alerta.setContentText("El usuario ingreso correctamente");
                    limpiarIngreso(correoUsuario, claveUsuario);
                    alerta.showAndWait();

                    return correo;

                } else {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setHeaderText(null);
                    alerta.setTitle("Autenticacion ");
                    alerta.setContentText("La clave del usuario no es valida");
                    limpiarIngreso(correoUsuario, claveUsuario);
                    alerta.showAndWait();

                    return null;

                }

            } else {

                loggger.info("El usuario no existe");
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setHeaderText(null);
                alerta.setTitle("Prueba ");
                alerta.setContentText("Usuario no registrado en la BD");
                limpiarIngreso(correoUsuario, claveUsuario);
                alerta.showAndWait();

                return null;

            }

        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Autenticacion del usuario");
            alerta.setContentText("Error: Correo o clave vacios");
            limpiarIngreso(correoUsuario, claveUsuario);
            alerta.showAndWait();
            return null;
        }

    }
}
