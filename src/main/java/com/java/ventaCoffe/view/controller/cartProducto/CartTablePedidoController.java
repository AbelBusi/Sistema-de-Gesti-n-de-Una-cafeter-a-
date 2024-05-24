package com.java.ventaCoffe.view.controller.cartProducto;

import com.java.ventaCoffe.controller.impl.ProductoServiceImpl;
import com.java.ventaCoffe.model.entity.Producto;
import com.java.ventaCoffe.view.controller.ControllerMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class CartTablePedidoController {

    ObservableList<Producto> productos;

    private final Logger loggger = LoggerFactory.getLogger(CartTablePedidoController.class);


    public void agregarPedidoTable(String nombrePedido,
                                   int cantidadPedido,
                                   double precioPedido,
                                   TableView<Producto> tableView) {
        try {

            loggger.info("Cantidad: {}",cantidadPedido);
            loggger.info("Nombre: {}",nombrePedido);
            loggger.info("Precio: {}",precioPedido);

            Producto producto = new Producto(cantidadPedido, nombrePedido, precioPedido);
            if (this.productos.contains(producto)) {
                this.productos.add(producto);
                tableView.setItems(productos);
            }
            {
                System.out.println("prueba testeo view");
            }
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }


    }

    public void MostrarProductos(TableView<Producto> tablProducto,
                                 TableColumn nombreProducto,
                                 TableColumn stocksProducto,
                                 TableColumn precioProducto) {
        productos = FXCollections.observableArrayList();

        nombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));


    }

}