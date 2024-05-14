package com.java.ventaCoffe.view.controller;

import com.java.ventaCoffe.model.entity.Data;
import com.java.ventaCoffe.view.controller.inventario.AgregarProductoController;
import com.java.ventaCoffe.view.controller.inventario.ImagenProductoController;
import com.java.ventaCoffe.view.controller.inventario.mostrarComboController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
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

    @FXML
    private ImageView imagenProductoView;

    private Image image;

    @Autowired
    private mostrarComboController comboController;


    @Autowired
    private ImagenProductoController imagenController;

    private String nombreUsuario;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @Autowired
    private AgregarProductoController agregarProducto;



    String ruta;

    public void agregarImagenProducto() {

        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open image File","*png","*jpg"));
        File file = openFile.showOpenDialog(menuForm.getScene().getWindow());

        if(file!=null){

            ruta = file.getAbsolutePath();
            image = new Image(file.toURI().toString(),137,135,false,true);
            imagenProductoView.setImage(image);

        }

    }

    @FXML
    void limpiarCasillas(ActionEvent event) {
        agregarProducto.limpiarCasillas(txtNombreProducto, txtStockProducto, txtPrecioProducto,imagenProductoView);
        ruta=null;
    }

    @FXML
    void imprimirBoton(ActionEvent event) {
        agregarImagenProducto();
    }

    @FXML
    void guardarProducto(ActionEvent event) {
        System.out.println("Prueba");
        agregarProducto.agregarProducto(txtNombreProducto, txtStockProducto, txtPrecioProducto, getNombreUsuario(),
                comboTipoProducto, comboEstadoProducto,ruta,imagenProductoView);
        System.out.println("Paso");
        ruta=null;

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
