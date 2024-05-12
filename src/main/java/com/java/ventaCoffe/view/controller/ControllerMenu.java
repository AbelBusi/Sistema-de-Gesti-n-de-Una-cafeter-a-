package com.java.ventaCoffe.view.controller;

import com.java.ventaCoffe.view.controller.minventario.AgregarProductoController;
import com.java.ventaCoffe.view.controller.minventario.mostrarComboController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class ControllerMenu implements Initializable {

    //private final Logger loggger = LoggerFactory.getLogger(ControllerMenu.class);

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

    @Autowired
    private mostrarComboController comboController;


    private String nombreUsuario;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @Autowired
    private AgregarProductoController agregarProducto;

    @FXML
    void limpiarCasillas(ActionEvent event) {
        agregarProducto.limpiarCasillas(txtNombreProducto, txtStockProducto, txtPrecioProducto);
    }

    @FXML
    void guardarProducto(ActionEvent event) {
        System.out.println("Prueba");
        agregarProducto.agregarProducto(txtNombreProducto, txtStockProducto, txtPrecioProducto, getNombreUsuario(), comboTipoProducto, comboEstadoProducto);
        System.out.println("Paso");
        //try {
        //}catch (RuntimeException e){
        //  System.out.println("Error: "+e.getMessage());
        //}
        //System.out.println("Paso el test");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {

            labelUsuarioLogeado.setText(getNombreUsuario());

        });
        agregarProducto.fijarlogitudMaximo(txtStockProducto,4);
        agregarProducto.fijarlogitudMaximo(txtPrecioProducto,6);
        comboController.recorrerEstadoProducto(comboEstadoProducto);
        comboController.recorrerTipoProducto(comboTipoProducto);

    }
}
