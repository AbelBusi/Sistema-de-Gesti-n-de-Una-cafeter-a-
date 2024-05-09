package com.java.ventaCoffe.view.controller;

import com.java.ventaCoffe.view.controller.login.CrearCuentaCntroller;
import com.java.ventaCoffe.view.controller.login.IngresaCuentaController;
import com.java.ventaCoffe.view.controller.login.RecuperarClaveController;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class ControllerLogin implements Initializable {

    private final Logger loggger = LoggerFactory.getLogger(ControllerLogin.class);

    @FXML
    private Button BCrearCuenta;

    @FXML
    private Button BCuentaExistente;

    @FXML
    private Button BIngresar;

    @FXML
    private Button buttonRegresarIncio;

    @FXML
    private Button BRegistrarse;

    @FXML
    private Label LCrearCuenta;

    @FXML
    private Label LIngresarUsuario;

    @FXML
    private Label LRegistrarse;

    @FXML
    private AnchorPane apEntrarRegistrarse;

    @FXML
    private AnchorPane apRecuperarClave;

    @FXML
    private AnchorPane apCambiarClave;

    @FXML
    private AnchorPane apIngresar;

    @FXML
    private AnchorPane apRegistrarse;

    @FXML
    private ComboBox<?> comboPregunta;

    @FXML
    private ComboBox<?> RComboPregunta;

    @FXML
    private Hyperlink hyClave;

    @FXML
    private PasswordField txtClave;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField RPCorreoIngresar;

    @FXML
    private PasswordField txtRegistrarClave;

    @FXML
    private TextField txtRegistrarseCorreo;

    @FXML
    private TextField txtRespuesta;

    @FXML
    private TextField RCPrespuesta;

    @FXML
    private PasswordField txtNuevaClave;

    @FXML
    private PasswordField txtNuevaClaveVerifi;

    //Validaciones

    @Autowired
    private IngresaCuentaController login;

    @Autowired
    private CrearCuentaCntroller crearCuenta;

    @Autowired
    private RecuperarClaveController recuperarClave;

    private String[] preguntas = {"¿Cual es tu color favorito?",
            "¿Cual es tu serie favorita?", "¿Mascota favorita?"};

    public void recorrePreguntas() {
        List<String> list = new ArrayList<>();
        for (String pregunta : preguntas) {

            list.add(pregunta);

        }

        ObservableList lisData = FXCollections.observableArrayList(list);
        comboPregunta.setItems(lisData);
        RComboPregunta.setItems(lisData);
    }

    @FXML
    void CrearCuenta(ActionEvent event) {

        crearCuenta.Registrarse(txtRegistrarseCorreo, txtRegistrarClave, comboPregunta, txtRespuesta);

    }

    @FXML
    void IngresarUsuario(ActionEvent event) throws Exception {

        String correoUsuario=login.Ingresar(txtCorreo, txtClave);

        if (correoUsuario!=null) {

            //TxtCorreo es seleccionado para poder verificar alg elemento de la escena actual, para asi acceder
            Stage ventanaLogin = (Stage) txtCorreo.getScene().getWindow();
            Stage ventanaMenu = new Stage();
            FXMLLoader ruta = new FXMLLoader();
            ruta.setLocation(getClass().getResource("/com/java/ventaCoffe/menu.fxml"));
            Parent root = ruta.load();
            ControllerMenu controllerMenu=ruta.getController();
            controllerMenu.setNombreUsuario(correoUsuario);
            Scene scene = new Scene(root);
            ventanaMenu.setScene(scene);
            ventanaMenu.show();
            ventanaLogin.close();
        }

    }

    public void cambiarForm(ActionEvent event) {


        TranslateTransition slider = new TranslateTransition();
        if (event.getSource() == BRegistrarse) {

            slider.setNode(apEntrarRegistrarse);
            slider.setToX(300);
            slider.setDuration(Duration.seconds(.5));
            slider.setOnFinished((ActionEvent e) -> {


                BRegistrarse.setVisible(false);
                BCuentaExistente.setVisible(true);

                //recorrePreguntas();
            });

            slider.play();


        } else if (event.getSource() == BCuentaExistente) {

            slider.setNode(apEntrarRegistrarse);
            slider.setToX(0);
            slider.setDuration(Duration.seconds(.5));
            slider.setOnFinished((ActionEvent e) -> {

                BCuentaExistente.setVisible(false);
                BRegistrarse.setVisible(true);
                apRecuperarClave.setVisible(false);
                apCambiarClave.setVisible(false);
                comboPregunta.cancelEdit();
                recuperarClave.limpiarPreguntas(RPCorreoIngresar, RCPrespuesta);

            });

            slider.play();


        }

    }

    @FXML
    void ClavePerdida(ActionEvent event) {

        apRecuperarClave.setVisible(true);

    }

    @FXML
    void RegresarInicio(ActionEvent event) {

        apRecuperarClave.setVisible(false);
        recuperarClave.limpiarPreguntas(RPCorreoIngresar, RCPrespuesta);

    }

    @FXML
    void ContinuarValidacion(ActionEvent event) {

        recuperarClave.VerificarCorreoExistente(RPCorreoIngresar, RComboPregunta, RCPrespuesta, apCambiarClave);


    }

    @FXML
    void RegresarRecuperarClave(ActionEvent event) {

        apCambiarClave.setVisible(false);

    }

    @FXML
    void CambiarClaveNueva(ActionEvent event) {

        recuperarClave.cambiarClaveUsuario(RPCorreoIngresar, RCPrespuesta, txtNuevaClave,
    txtNuevaClaveVerifi, apCambiarClave, apRecuperarClave);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        recorrePreguntas();
    }
}