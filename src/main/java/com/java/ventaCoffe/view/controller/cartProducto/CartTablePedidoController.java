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

    private final ObservableList<Producto> productos = FXCollections.observableArrayList();

    private String nombrePedido;

    private int stockPedido;

    private double precioPedido;

    public String getNombrePedido() {
        return nombrePedido;
    }

    public void setNombrePedido(String nombrePedido) {
        this.nombrePedido = nombrePedido;
    }

    public int getStockPedido() {
        return stockPedido;
    }

    public void setStockPedido(int stockPedido) {
        this.stockPedido = stockPedido;
    }

    public double getPrecioPedido() {
        return precioPedido;
    }

    public void setPrecioPedido(double precioPedido) {
        this.precioPedido = precioPedido;
    }

    private final Logger loggger = LoggerFactory.getLogger(CartTablePedidoController.class);

    public void initializable(String nombrePedido, int cantidadPedido, double precioPedido) {


        if (!(nombrePedido == null)
                && !(precioPedido == 0.0) && !(cantidadPedido == 0)) {
            Producto producto = new Producto(cantidadPedido, nombrePedido, precioPedido);
            productos.add(producto);
        } else {
            System.out.println("No paso el test de la tablaView");
        }


    }


    public void agregarPedidoTable(TableView<Producto> tableView,
                                   TableColumn<Producto, String> columNombre,
                                   TableColumn<Producto, Integer> columCantidad,
                                   TableColumn<Producto, Double> columPrecio) {


        columNombre.setCellValueFactory(new PropertyValueFactory("nombreProducto"));
        columCantidad.setCellValueFactory(new PropertyValueFactory("stockProducto"));
        columPrecio.setCellValueFactory(new PropertyValueFactory("precioProducto"));
        tableView.setItems(productos);
    }



}