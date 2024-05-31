package com.java.ventaCoffe.view.controller.cartProducto;

import com.java.ventaCoffe.model.entity.PedidoTemporal;
import com.java.ventaCoffe.model.entity.Producto;
import com.java.ventaCoffe.view.controller.ControllerMenu;
import jakarta.persistence.criteria.CriteriaBuilder;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PedidoController {

    private final Logger logger = LoggerFactory.getLogger(PedidoController.class);


    public Integer ObtenerIdPedidoTemp(TableView<PedidoTemporal> tableView) {
        PedidoTemporal pedidoTemporal = tableView.getSelectionModel().getSelectedItem();
        try {
            Integer num = tableView.getSelectionModel().getSelectedIndex();
            if (!((num - 1) < -1)) {
                Integer id = pedidoTemporal.getIdPedidoTemp();
                logger.info("El pedido seleccionado es : {}", id);

                return id;
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de tipado");
                alert.setHeaderText(null);
                alert.setContentText("Debe seleccionar un pedido para ejeuctar la operacion");
                alert.showAndWait();
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de tipado");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar una casilla de la tabla");
            alert.showAndWait();
            return null;
        }

    }

}
