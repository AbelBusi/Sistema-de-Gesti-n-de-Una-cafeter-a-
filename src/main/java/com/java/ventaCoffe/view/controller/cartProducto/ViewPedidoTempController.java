package com.java.ventaCoffe.view.controller.cartProducto;

import com.java.ventaCoffe.controller.service.IPedidoTempService;
import com.java.ventaCoffe.model.entity.PedidoTemporal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ViewPedidoTempController {

    @Autowired
    private IPedidoTempService pedidoTempService;

    private final ObservableList<PedidoTemporal> pedidos= FXCollections.observableArrayList();

    public void mostrarTablePedidoTemp(TableView<PedidoTemporal> tableView,
                                       TableColumn<PedidoTemporal,Integer> ColumnCantidad,
                                       TableColumn<PedidoTemporal,Double> ColumnPrecio,
                                       TableColumn<PedidoTemporal,String> ColumnNombre){
        List<PedidoTemporal> ListPedidos= pedidoTempService.mostrarPedidos();

        try {

            ColumnCantidad.setCellValueFactory(new PropertyValueFactory<PedidoTemporal,Integer>("cantidadPedidoTemp"));
            ColumnPrecio.setCellValueFactory(new PropertyValueFactory<PedidoTemporal,Double>("precioPedidoTemp"));
            ColumnNombre.setCellValueFactory(new PropertyValueFactory<PedidoTemporal,String>("nombrePedidoTemp"));

            pedidos.clear();
            pedidos.addAll(ListPedidos);
            tableView.setItems(pedidos);
            tableView.refresh();

        }catch (NullPointerException exception){

            System.out.println("Error: "+exception.getMessage());
        }


    }

}
