package com.java.ventaCoffe.view.controller.compraProducto;

import com.java.ventaCoffe.controller.impl.PedidoServiceImpl;
import com.java.ventaCoffe.controller.impl.UsuarioServiceImpl;
import com.java.ventaCoffe.model.entity.Pedido;
import com.java.ventaCoffe.model.entity.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class MostrarPedidoController {

    @Autowired
    private PedidoServiceImpl pedidoService;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    public void mostrarTablaPedido(TableView<Pedido> pedidosTable,
                                   TableColumn<Pedido,Integer> idPedidoColumn,
                                   TableColumn<Pedido,Double> TotalPedidoColumn,
                                   TableColumn<Pedido, LocalDateTime> FechaPedidoColumn,
                                   TableColumn<Pedido, Usuario> usuarioPedidoColumn){

        List<Pedido> pedidos = pedidoService.findAll();
        ObservableList<Pedido> pedidoObservableList = FXCollections.observableArrayList();
        try {


            idPedidoColumn.setCellValueFactory(new PropertyValueFactory<>("idPedido"));
            TotalPedidoColumn.setCellValueFactory(new PropertyValueFactory<>("totalPedido"));
            FechaPedidoColumn.setCellValueFactory(new PropertyValueFactory<>("fechaPedido"));
            usuarioPedidoColumn.setCellValueFactory(new PropertyValueFactory<>("usuario"));
            usuarioPedidoColumn.setCellFactory(column -> new TableCell<Pedido, Usuario>() {
                @Override
                protected void updateItem(Usuario usuario, boolean empty) {
                    super.updateItem(usuario, empty);
                    if (empty || usuario == null) {
                        setText(null);
                    } else {
                        setText(usuario.getCorreoUsuario());
                    }
                }
            });
            pedidoObservableList.addAll(pedidos);
            pedidosTable.setItems(pedidoObservableList);

        }catch (Exception exception){
            System.out.println("Error: "+exception.getMessage());
        }

    }

}
