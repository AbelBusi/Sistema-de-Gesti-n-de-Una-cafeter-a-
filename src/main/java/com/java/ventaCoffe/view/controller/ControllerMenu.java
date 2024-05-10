package com.java.ventaCoffe.view.controller;

import com.java.ventaCoffe.controller.impl.UsuarioServiceImpl;
import com.java.ventaCoffe.view.controller.inventario.AgregarProductoContoller;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.ArrayList;
import java.util.List;
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
    private ComboBox<?> comboEstadoProducto;

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

    private String []tipoProductoA ={"Bebida","Enlatado","postre","bocadito"};

    private String []estadoProductoA={"Disponible", "No Disponible"};

    @Autowired
    private AgregarProductoContoller agregarProductoContoller;

    public void recorrerTipoProducto(){

        List<String> list= new ArrayList<>();

        for (String tipo: tipoProductoA){
            list.add(tipo);
        }
        ObservableList lisData = FXCollections.observableArrayList(list);
        comboTipoProducto.setItems(lisData);
    }

    public void recorrerEstadoProducto(){
        List<String> list= new ArrayList<>();

        for (String tipo: estadoProductoA){
            list.add(tipo);
        }
        ObservableList lisData = FXCollections.observableArrayList(list);
        comboEstadoProducto.setItems(lisData);
    }
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

        agregarProductoContoller.agregarProducto(txtNombreProducto,
                txtStockProducto,
                txtPrecioProducto,
                comboTipoProducto,
                comboEstadoProducto,txtIdProducto);

    }

    @FXML
    void limpiarCasillas(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(()->{

            labelUsuarioLogeado.setText(getNombreUsuario());

        });

        recorrerEstadoProducto();
        recorrerTipoProducto();

    }
}
