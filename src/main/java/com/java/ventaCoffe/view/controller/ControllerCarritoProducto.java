package com.java.ventaCoffe.view.controller;

import com.java.ventaCoffe.model.entity.DataTransFerService;
import com.java.ventaCoffe.model.entity.Producto;
import com.java.ventaCoffe.view.controller.cartProducto.CartTablePedidoController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class ControllerCarritoProducto implements Initializable {

    private final Logger loggger = LoggerFactory.getLogger(ControllerCarritoProducto.class);

    @Autowired
    private ApplicationContext applicationContext;

    @FXML
    private AnchorPane anchorPane;


    @FXML
    private Button BcartAgregarProducto;

    @FXML
    private Spinner<Integer> BcartCantidadProducto;

    @FXML
    private Label LcartNombreProducto;

    @FXML
    private Label LcartPrecioProducto;

    @FXML
    private ImageView cartImageProducto;

    private String nombreProducto;

    private double precioProducto;

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }


    //el otro argumento es la cantidad de productos (agregaProducto)

    public void agregarProducto(Producto producto) {

        try {
            LcartNombreProducto.setText(producto.getNombreProducto());
            LcartPrecioProducto.setText("$" + producto.getPrecioProducto());
            String path = "File:" + producto.getImagenProducto();
            Image image = new Image(path, 200, 85, false, true);
            loggger.info("Path: {}", path);
            cartImageProducto.setImage(image);
            String nombreProducto = LcartNombreProducto.getText();
            double precioProducto = producto.getPrecioProducto();
            setNombreProducto(nombreProducto);
            setPrecioProducto(precioProducto);
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }

    }

    @Autowired
    private DataTransFerService dataTransFerService;

    @FXML
    void BagregarProducto(ActionEvent event) {
        try {
            int cantidad = BcartCantidadProducto.getValue();
            String nombrePedido=getNombreProducto();
            double precioPedido = getPrecioProducto();

            dataTransFerService.setNombrePedido(nombrePedido);
            dataTransFerService.setPrecioPedido(precioPedido);
            dataTransFerService.setCantidad(cantidad);

        } catch (Exception exception) {
            loggger.error("Error: {}", exception.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
