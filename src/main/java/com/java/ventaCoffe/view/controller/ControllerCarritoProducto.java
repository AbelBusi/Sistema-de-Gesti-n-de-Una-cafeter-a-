package com.java.ventaCoffe.view.controller;

import com.java.ventaCoffe.model.entity.Producto;
import com.java.ventaCoffe.view.controller.inventario.ActualizarProductoContoller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class ControllerCarritoProducto implements Initializable {

    private final Logger loggger = LoggerFactory.getLogger(ControllerCarritoProducto.class);


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
        String path= "File:"+producto.getImagenProducto();
        Image image = new Image(path,200,85,false,true);
        loggger.info("Path: {}",path);
        cartImageProducto.setImage(image);

        //cartImageProducto.setImage(new Image(producto.getImagenProducto()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
