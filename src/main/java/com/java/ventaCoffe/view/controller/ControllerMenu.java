package com.java.ventaCoffe.view.controller;

import com.java.ventaCoffe.controller.impl.UsuarioServiceImpl;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;


@Component
public class ControllerMenu implements Initializable {

    private final Logger loggger = LoggerFactory.getLogger(ControllerMenu.class);

    //Pertenecen a agregar productos al 'INVENTARIO'

    @FXML
    private Button BagregarProducto;

    @FXML
    private Button BlimpiarProducto;

    @FXML
    private Label LidProducto;

    @FXML
    private Label LnombreProducto;

    @FXML
    private Label LprecioProducto;

    @FXML
    private Label LstockProducto;

    @FXML
    private Label LtipoProducto;

    @FXML
    private TableView<?> TableProductoInv;

    @FXML
    private ComboBox<?> comboTipoProducto;

    @FXML
    private Label labelUsuarioLogeado;

    @FXML
    private AnchorPane menuForm;

    @FXML
    private TextField txtIdProducto;

    @FXML
    private TextField txtNombreProducto;

    @FXML
    private TextField txtPrecioProducto;

    @FXML
    private TextField txtStockProducto;

    private String nombreUsuario;

    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @FXML
    void guardarProducto(ActionEvent event) {

    }

    @FXML
    void limpiarCasillas(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(()->{

            labelUsuarioLogeado.setText(getNombreUsuario());

        });

    }
}
