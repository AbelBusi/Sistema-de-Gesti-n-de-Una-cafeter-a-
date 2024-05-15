package com.java.ventaCoffe.view.controller.inventario;

import com.java.ventaCoffe.controller.impl.ProductoServiceImpl;
import com.java.ventaCoffe.model.entity.Producto;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActualizarProductoContoller {


    @Autowired
    private ProductoServiceImpl productoService;

    public void ObtenerTable(TableView<Producto> tableView,TextField idProducto, TextField nombreProducto, TextField stockProducto,
                             TextField precioProducto){
        Producto producto = tableView.getSelectionModel().getSelectedItem();
        int num = tableView.getSelectionModel().getSelectedIndex();
        if ((num -1)<-1)return;
        System.out.println(producto.getIdProducto());
        idProducto.setText(String.valueOf(producto.getIdProducto()));
        nombreProducto.setText(producto.getNombreProducto());
        stockProducto.setText(String.valueOf(producto.getStockProducto()));
        precioProducto.setText(String.valueOf(producto.getPrecioProducto()));

    }


}
