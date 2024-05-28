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

    private final Logger loggger = LoggerFactory.getLogger(CartTablePedidoController.class);

    private String nombreProducto;

    private int cantidad;

    private double precioProductoM;

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioProductoM() {
        return precioProductoM;
    }

    public void setPrecioProductoM(double precioProductoM) {
        this.precioProductoM = precioProductoM;
    }

    public void agregarPedidoTable(TableView<Producto> tableView,
                                   TableColumn<Producto, String> columNombre,
                                   TableColumn<Producto, Integer> columCantidad,
                                   TableColumn<Producto, Double> columPrecio) {

        loggger.info("Entrando al tableView Pedido para depuración");
        if (!productos.isEmpty()) {
            // Configuración de las columnas con las propiedades correctas
            columNombre.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
            columCantidad.setCellValueFactory(new PropertyValueFactory<>("stockProducto"));
            columPrecio.setCellValueFactory(new PropertyValueFactory<>("precioProducto"));

            tableView.setItems(productos);
            loggger.info("TableView configurada correctamente. Tamaño de la lista: {}", productos.size());
        } else {
            loggger.info("No se muestran datos en la tableView");
        }
    }


    public void initializable(String nombrePedido, int cantidadPedido, double precioPedido) {

        loggger.info("Entrando en el table");


        if (!(nombrePedido == null)
                && !(precioPedido == 0.0) && !(cantidadPedido == 0)) {
            Producto producto = new Producto(1, "nombrePedido", 2.5);
            productos.add(producto);

        } else {
            System.out.println("No paso el test de la tablaView");
        }

    }



}