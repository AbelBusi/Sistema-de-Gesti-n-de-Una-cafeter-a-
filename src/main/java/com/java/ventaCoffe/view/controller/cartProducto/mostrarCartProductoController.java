package com.java.ventaCoffe.view.controller.cartProducto;

import com.java.ventaCoffe.controller.impl.ProductoServiceImpl;
import com.java.ventaCoffe.model.entity.Producto;
import com.java.ventaCoffe.view.controller.ControllerCarritoProducto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;

@Component
public class mostrarCartProductoController {

    @Autowired
    private ProductoServiceImpl productoService;

    private ObservableList<Producto> cardListData = FXCollections.observableArrayList();

    public ObservableList<Producto> menuGetData() {

        ObservableList<Producto> listData = FXCollections.observableArrayList();

        try {

            List<Producto> productos = productoService.mostrarProducto();

            listData.addAll(productos);


        } catch (Exception exception) {

            System.out.println("Error: " + exception.getMessage());

        }

        return listData;

    }


    public void menuDisplayCard(GridPane menuGridPane) {
        cardListData.clear();
        cardListData.addAll(menuGetData());

        int row = 0;
        int column = 0;

        menuGridPane.getRowConstraints().clear();
        menuGridPane.getColumnConstraints().clear();

        for (int q = 0; q < cardListData.size(); q++) {
            try {
                // Asegúrate de que la ruta al FXML es correcta
                URL fxmlUrl = getClass().getResource("/com/java/ventaCoffe/carritoProductos.fxml");
                System.out.println("FXML URL: " + fxmlUrl); // Verificación
                if (fxmlUrl == null) {
                    throw new RuntimeException("FXML file not found: /com/java/ventaCoffe/carritoProductos.fxml");
                }
                FXMLLoader load = new FXMLLoader(fxmlUrl);

                AnchorPane pane = load.load();
                ControllerCarritoProducto carritoProducto = load.getController();
                carritoProducto.agregarProducto(cardListData.get(q));

                if (column == 2) {
                    column = 0;
                    row += 1;
                }

                menuGridPane.add(pane, column++, row);

            } catch (Exception exception) {
                System.out.println("Error: " + exception.getMessage());
                exception.printStackTrace(); // Añade esta línea para obtener más detalles del error
            }
        }
    }


}
