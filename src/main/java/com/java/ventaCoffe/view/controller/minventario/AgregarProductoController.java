package com.java.ventaCoffe.view.controller.minventario;

import com.java.ventaCoffe.controller.impl.ProductoServiceImpl;
import com.java.ventaCoffe.controller.impl.UsuarioServiceImpl;
import com.java.ventaCoffe.model.dto.ProductoDto;
import com.java.ventaCoffe.model.entity.Producto;
import com.java.ventaCoffe.model.entity.Usuario;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class AgregarProductoController {

    private final Logger logger = LoggerFactory.getLogger(AgregarProductoController.class);

    @Autowired
    private ProductoServiceImpl productoService;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    public void alertaUsuarioExistente() {

        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setHeaderText(null);
        alerta.setTitle("Producto");
        alerta.setContentText("El producto que desea agregar, ya existe en el inventario");
        alerta.showAndWait();
    }

    public void alertaCasillasVacias() {

        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setHeaderText(null);
        alerta.setTitle("Producto");
        alerta.setContentText("Las cajas de texto no pueden estar vacias");
        alerta.showAndWait();
    }

    public void prueba(String parametro){

        System.out.println(parametro);

    }


    public void agregarProducto(TextField nombreProducto,
                                TextField stockProducto,
                                TextField precioProducto,
                                String idCorreo,
                                ComboBox tipoProducto,
                                ComboBox estadoProducto)
    {
        try {
        System.out.println("en el metodo");
            logger.info("Test: {}, {}, {}, {}",nombreProducto.getText(),
                    stockProducto.getText(),precioProducto.getText(),
                    idCorreo);
            String nombre = nombreProducto.getText();
            String tipo = (String) tipoProducto.getSelectionModel().getSelectedItem();
            String estado = (String) estadoProducto.getSelectionModel().getSelectedItem();
            String usuario = idCorreo;
            Optional<Producto> productoExistente = productoService.findByNombreProducto(nombre);
            Optional<Usuario> usuarioProducto = usuarioService.findByCorreoUsuario(usuario);
            int idUsuarioProducto = usuarioProducto.get().getIdUsuario();

            logger.info("Prueba: {}, User: {}", nombre,usuario);

            if (!productoExistente.isPresent()) {

                if (!nombre.isEmpty() && !stockProducto.getText().isEmpty() && !precioProducto.getText().isEmpty()
                        && !tipo.isEmpty() && !estado.isEmpty() && !idCorreo.isEmpty()) {

                    int stock = Integer.parseInt(stockProducto.getText());
                    double precio = Double.parseDouble(precioProducto.getText());
                    Optional<Usuario> idUsuario = usuarioService.idUsuario(idUsuarioProducto);

                    ProductoDto productoDto = new ProductoDto();
                    productoDto.setNombreProducto(nombre);
                    productoDto.setTipoProducto(tipo);
                    productoDto.setStockProducto(stock);
                    productoDto.setPrecioProducto(precio);
                    productoDto.setEstadoProducto(estado);
                    productoDto.setFechaProducto(LocalDateTime.now());
                    productoDto.setUsuario(idUsuario.orElse(null));

                    Producto producto = productoService.guardarProducto(productoDto);


                } else {
                    alertaCasillasVacias();
                }

            } else {
                alertaUsuarioExistente();
            }

        } catch (RuntimeException e) {

            System.out.println("Error: " + e.getMessage());

        }


    }

}
