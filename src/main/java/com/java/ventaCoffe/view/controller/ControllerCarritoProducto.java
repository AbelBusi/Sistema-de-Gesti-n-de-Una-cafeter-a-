package com.java.ventaCoffe.view.controller;

import com.java.ventaCoffe.model.entity.Producto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.stereotype.Controller;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class ControllerCarritoProducto implements Initializable {

    @FXML
    private Button BcartAgregarProducto;

    @FXML
    private Spinner<?> BcartCantidadProducto;

    @FXML
    private Label LcartNombreProducto;

    @FXML
    private Label LcartPrecioProducto;

    @FXML
    private ImageView cartImageProducto;

    public void agregarProducto(Producto producto){

        LcartNombreProducto.setText(producto.getNombreProducto());
        LcartPrecioProducto.setText("$"+producto.getPrecioProducto());

        System.out.println("Imagen prueba");

        //cartImageProducto.setImage(new Image(producto.getImagenProducto()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
