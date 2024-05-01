package com.java.ventaCoffe.view.controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ControllerLogin implements Initializable {

    @FXML
    private Button BCrearCuenta;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}