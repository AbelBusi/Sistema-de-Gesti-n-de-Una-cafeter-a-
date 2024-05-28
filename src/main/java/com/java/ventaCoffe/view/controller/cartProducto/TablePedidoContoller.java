package com.java.ventaCoffe.view.controller.cartProducto;

import com.java.ventaCoffe.model.entity.Producto;
import javafx.scene.control.TableView;
import org.springframework.stereotype.Component;

@Component
public class TablePedidoContoller {

    public void SeleecionarPedidoTable(TableView<Producto> productoTableView){

        Producto p = productoTableView.getSelectionModel().getSelectedItem();
        if(p==null){
            System.out.println("Debe seleccionar un producto");
        }else {

            System.out.println("Nombre Producto: "+p.getNombreProducto());

        }

    }

}
