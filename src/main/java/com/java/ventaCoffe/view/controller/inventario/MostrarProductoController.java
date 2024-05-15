package com.java.ventaCoffe.view.controller.inventario;

import com.java.ventaCoffe.controller.impl.ProductoServiceImpl;
import com.java.ventaCoffe.model.entity.Producto;
import com.java.ventaCoffe.view.controller.error.Errores;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class MostrarProductoController {

    @Autowired
    private ProductoServiceImpl productoService;

    Errores errores = new Errores();

    public void prueba (){

        ObservableList<Producto> productos = FXCollections.observableArrayList();

        List<Producto>  listProducto =productoService.mostrarProducto();
    }

    public void MostrarProductos(TableView<Producto> tablProducto ,
                                 TableColumn<Producto,Integer> idProducto,
                                 TableColumn<Producto,String> nombreProducto,
                                 TableColumn<Producto,String> tipoProducto,
                                 TableColumn<Producto,Integer> stocksProducto,
                                 TableColumn<Producto,Double> precioProducto,
                                 TableColumn<Producto,String> estadoProducto,
                                 TableColumn<Producto, LocalDateTime> fechaProducto) {

        ObservableList<Producto> productos = FXCollections.observableArrayList();

        List<Producto>  listProducto =productoService.mostrarProducto();

        try {
            tablProducto.setPlaceholder(new Label("No hay datos por mostrar"));
            idProducto.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
            idProducto.setStyle("-fx-alignment: CENTER-LEFT");
            nombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
            nombreProducto.setStyle("-fx-alignment: CENTER-LEFT");
            tipoProducto.setCellValueFactory(new PropertyValueFactory<>("tipoProducto"));
            tipoProducto.setStyle("-fx-alignment: CENTER-LEFT");
            stocksProducto.setCellValueFactory(new PropertyValueFactory<>("stockProducto"));
            stocksProducto.setStyle("-fx-alignment: CENTER-LEFT");
            precioProducto.setCellValueFactory(new PropertyValueFactory<>("precioProducto"));
            precioProducto.setStyle("-fx-alignment: CENTER-LEFT");
            estadoProducto.setCellValueFactory(new PropertyValueFactory<>("estadoProducto"));
            estadoProducto.setStyle("-fx-alignment: CENTER-LEFT");
            fechaProducto.setCellValueFactory(new PropertyValueFactory<>("fechaProducto"));
            fechaProducto.setStyle("-fx-alignment: CENTER-LEFT");

            productos.clear();

            productos.addAll(listProducto);


            tablProducto.setItems(productos);

        }catch (IllegalStateException exception){

            System.out.println("Error: "+exception.getMessage());
            errores.errorDatos();

        }

    }


}
