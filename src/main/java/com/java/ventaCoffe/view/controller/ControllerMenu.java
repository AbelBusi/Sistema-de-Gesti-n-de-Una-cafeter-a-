package com.java.ventaCoffe.view.controller;

import com.java.ventaCoffe.view.controller.minventario.AgregarProductoController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.ArrayList;
import java.util.List;
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

    private String []tipoProductoA ={"Bebida","Enlatado","postre","bocadito"};

    private String []estadoProductoA={"Disponible", "No Disponible"};



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

    @FXML
    void limpiarCasillas(ActionEvent event) {

    }

    @Autowired
    private AgregarProductoController agregarProducto;

    @FXML
    void guardarProducto(ActionEvent event) {
        System.out.println("Prueba");
        agregarProducto.agregarProducto(txtNombreProducto, txtStockProducto, txtPrecioProducto, getNombreUsuario(),comboTipoProducto,comboEstadoProducto);
        System.out.println("Paso");
        //try {
        //}catch (RuntimeException e){
          //  System.out.println("Error: "+e.getMessage());
        //}
        //System.out.println("Paso el test");
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
