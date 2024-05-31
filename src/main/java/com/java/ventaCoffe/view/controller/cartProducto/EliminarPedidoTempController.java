package com.java.ventaCoffe.view.controller.cartProducto;

import com.java.ventaCoffe.controller.impl.PedidoTempServiceImpl;
import com.java.ventaCoffe.view.controller.ControllerMenu;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EliminarPedidoTempController {

    private final Logger logger = LoggerFactory.getLogger(EliminarPedidoTempController.class);


    @Autowired
    private PedidoTempServiceImpl pedidoTempService;

    public void eliminarPedidoTemporal(Integer id){
        try {
            Integer idPedido =id;
            if(!(idPedido==null)){

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Eliminar pedido");
                alert.setHeaderText(null);
                alert.setContentText("¿Desea eliminar el pedido?");
                Optional<ButtonType> option=alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Eliminar pedido");
                    alerta.setHeaderText(null);
                    alerta.setContentText("Producto eliminado");
                    alerta.showAndWait();
                    pedidoTempService.eliminarPedidoId(idPedido);
                }else {

                    logger.info("Producto no eliminado del pedido");
                }

            }else {

                System.out.println("No puede eliminar un pedido null");

            }

        }catch (Exception exception){
            System.out.println("Error: "+exception.getMessage());
        }
    }

}
