package com.java.ventaCoffe.view.controller;


import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ControllerLogin implements Initializable {

    @FXML
    private Button BCrearCuenta;

    @FXML
    private Button BCuentaExistente;

    @FXML
    private Button BIngresar;

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
    private AnchorPane apIngresar;

    @FXML
    private AnchorPane apRegistrarse;

    @FXML
    private ComboBox<?> comboPregunta;

    @FXML
    private Hyperlink hyClave;

    @FXML
    private PasswordField txtClave;

    @FXML
    private TextField txtCorreo;

    @FXML
    private PasswordField txtRegistrarClave;

    @FXML
    private TextField txtRegistrarseCorreo;

    @FXML
    private TextField txtRespuesta;

    public void cambiarForm(ActionEvent event){

        TranslateTransition slider =new TranslateTransition();
        if(event.getSource()==BRegistrarse){

            slider.setNode(apEntrarRegistrarse);
            slider.setToX(300);
            slider.setDuration(Duration.seconds(.5));
            slider.setOnFinished((ActionEvent e) ->{


                BRegistrarse.setVisible(false);
                BCuentaExistente.setVisible(true);
            });

            slider.play();


        }else if(event.getSource()==BCuentaExistente){

            slider.setNode(apEntrarRegistrarse);
            slider.setToX(0);
            slider.setDuration(Duration.seconds(.5));
            slider.setOnFinished((ActionEvent e) ->{

                BCuentaExistente.setVisible(false);
                BRegistrarse.setVisible(true);

            });

            slider.play();


        }

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}