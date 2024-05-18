package com.java.ventaCoffe.view.controller.inventario;

import com.java.ventaCoffe.controller.impl.ProductoServiceImpl;
import com.java.ventaCoffe.model.entity.Producto;
import com.java.ventaCoffe.view.controller.error.Errores;
import jakarta.persistence.criteria.CriteriaBuilder;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EliminarProductoController {

    private final Logger logger = LoggerFactory.getLogger(EliminarProductoController.class);


    @Autowired
    private ProductoServiceImpl productoService;

    Errores errores = new Errores();

    public void limpiarCasillas(TextField idProducto,
                                TextField nombreProducto,
                                TextField stockProducto,
                                TextField precioProducto) {

        idProducto.setText("");
        nombreProducto.setText("");
        stockProducto.setText("");
        precioProducto.setText("");


    }

    public void eliminarProducto(TextField id) {

        try {
            if (!id.getText().isEmpty()) {
                Integer idProducto = Integer.parseInt(id.getText());
                Optional<Producto> producto = productoService.findById(idProducto);

                if (producto.isPresent()) {
                    logger.info("producto: {}", producto);
                    productoService.eliminarProducto(idProducto);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Gestion de productos");
                    alert.setContentText("Producto eliminado corectamente");
                    alert.showAndWait();

                } else {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Gestion de productos");
                    alert.setContentText("Seleccione un producto existente");
                    alert.showAndWait();

                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Gestion de productos");
                alert.setContentText("No debe estar vacio el id");
                alert.showAndWait();
            }
        } catch (NullPointerException exception) {
            System.out.println("Error: " + exception.getMessage());
            errores.errorDatos();
        }

    }

}
