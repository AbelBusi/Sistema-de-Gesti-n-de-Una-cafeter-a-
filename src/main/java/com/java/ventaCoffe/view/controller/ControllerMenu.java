package com.java.ventaCoffe.view.controller;

import com.java.ventaCoffe.VentaCoffeApplication;
import com.java.ventaCoffe.model.entity.Data;
import com.java.ventaCoffe.model.entity.Producto;
import com.java.ventaCoffe.view.controller.inventario.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
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
    private TableView<Producto> TableProductoInv;

    @FXML
    private TableColumn<Producto, String> columEstadoProducto;

    @FXML
    private TableColumn<Producto, LocalDateTime> columFechaProducto;

    @FXML
    private TableColumn<Producto, Double> columPrecioProducto;

    @FXML
    private TableColumn<Producto, String> columProducto;

    @FXML
    private TableColumn<Producto, Integer> columProductoID;

    @FXML
    private TableColumn<Producto, Integer> columStockProducto;

    @FXML
    private TableColumn<Producto, String> columTipoProducto;

    @FXML
    private ComboBox<String> comboTipoProducto;

    @FXML
    private ComboBox<String> comboEstadoProducto;

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

    private Integer idProductoSeleccionado;

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

    @Autowired
    private MostrarProductoController mostrarProductoController;

    @Autowired
    private ActualizarProductoContoller actualizarProductoContoller;

    @Autowired
    private EliminarProductoController eliminarProductoController;

    String ruta;

    @FXML
    void salirPrograma(ActionEvent event) {

//        try {
//        Stage ventanaMenu = (Stage) labelUsuarioLogeado.getScene().getWindow();
//        Stage ventanaLogin = new Stage();
//        FXMLLoader ruta = new FXMLLoader();
//        ruta.setLocation(getClass().getResource("/com/java/ventaCoffe/login.fxml"));
//        // Configura la fábrica de controladores de FXMLLoader para utilizar el contexto de Spring
//        ventanaLogin.setScene(scene);
//        ventanaLogin.show();
//        ventanaMenu.close();
//        }catch (NullPointerException exception){}

    }

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
        agregarProducto.limpiarCasillas(txtIdProducto,txtNombreProducto, txtStockProducto, txtPrecioProducto,
                imagenProductoView);
        ruta=null;
        idProductoSeleccionado=null;

    }

    @FXML
    void imprimirBoton(ActionEvent event) {
        agregarImagenProducto();
    }

    @FXML
    void guardarProducto(ActionEvent event) {
        System.out.println("Prueba");
        agregarProducto.agregarProducto(txtIdProducto,txtNombreProducto, txtStockProducto, txtPrecioProducto,
                getNombreUsuario(),
                comboTipoProducto, comboEstadoProducto,ruta,imagenProductoView);
        System.out.println("Paso");
        ruta=null;
        mostrarProductoController.MostrarProductos(TableProductoInv,columProductoID,
                columProducto,columTipoProducto,columStockProducto,columPrecioProducto,columEstadoProducto,
                columFechaProducto);
        idProductoSeleccionado=null;

    }


    public void SeleccionarTableView() {
        actualizarProductoContoller.ObtenerTable(TableProductoInv,txtIdProducto,txtNombreProducto,txtStockProducto,
                txtPrecioProducto,imagenProductoView);
        idProductoSeleccionado= actualizarProductoContoller.ObtenerTable(TableProductoInv,txtIdProducto,
                txtNombreProducto,txtStockProducto,txtPrecioProducto,imagenProductoView);
        loggger.info("Prueba Id Producto: {}",idProductoSeleccionado);

    }

    @FXML
    void actualizarProducto(ActionEvent event) {
        actualizarProductoContoller.ActualizarProducto(txtIdProducto,idProductoSeleccionado,
                txtNombreProducto,txtStockProducto,txtPrecioProducto,comboEstadoProducto,comboTipoProducto,ruta,
                imagenProductoView);
        mostrarProductoController.MostrarProductos(TableProductoInv,columProductoID,
                columProducto,columTipoProducto,columStockProducto,columPrecioProducto,columEstadoProducto,
                columFechaProducto);
        idProductoSeleccionado=null;

    }

    @FXML
    void TeliminarProducto(ActionEvent event) {

        eliminarProductoController.eliminarProducto(txtIdProducto);
        mostrarProductoController.MostrarProductos(TableProductoInv,columProductoID,
                columProducto,columTipoProducto,columStockProducto,columPrecioProducto,columEstadoProducto,
                columFechaProducto);
        agregarProducto.limpiarCasillas(txtIdProducto,txtNombreProducto, txtStockProducto, txtPrecioProducto,
                imagenProductoView);
        idProductoSeleccionado=null;

    }

    //Otra informacion


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {

            labelUsuarioLogeado.setText(getNombreUsuario());

        });
        agregarProducto.fijarlogitudMaximo(txtStockProducto,4);
        agregarProducto.fijarlogitudMaximo(txtPrecioProducto,6);
        comboController.recorrerEstadoProducto(comboEstadoProducto);
        comboController.recorrerTipoProducto(comboTipoProducto);
        mostrarProductoController.MostrarProductos(TableProductoInv,columProductoID,
                columProducto,columTipoProducto,columStockProducto,columPrecioProducto,columEstadoProducto,
                columFechaProducto);


    }
}
