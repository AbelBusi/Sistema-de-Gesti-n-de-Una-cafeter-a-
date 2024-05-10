package com.java.ventaCoffe.view.controller.inventario;

import com.java.ventaCoffe.controller.impl.ProductoServiceImpl;
import com.java.ventaCoffe.controller.impl.UsuarioServiceImpl;
import com.java.ventaCoffe.model.dto.ProductoDto;
import com.java.ventaCoffe.model.entity.Producto;
import com.java.ventaCoffe.model.entity.Usuario;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AgregarProductoContoller {

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
        alerta.setContentText("El producto que desea agregar, ya existe en el inventario");
        alerta.showAndWait();
    }


    public void agregarProducto(TextField nombreProducto, TextField stockProducto, TextField precioProducto
            , ComboBox tipoProducto
            , ComboBox estadoProducto
            , TextField idCorreo
    ) {
        try {

            String nombre = nombreProducto.getText();
            String tipo = (String) tipoProducto.getSelectionModel().getSelectedItem();
            String estado = (String) estadoProducto.getSelectionModel().getSelectedItem();
            String usuario = "admin";
            Optional<Producto> productoExistente = productoService.findByNombreProducto(nombre);
            Optional<Usuario> usuarioProducto = usuarioService.findByCorreoUsuario(usuario);
            int idUsuarioProducto = usuarioProducto.get().getIdUsuario();


            if (!productoExistente.isPresent()) {

                if (!nombre.isEmpty() && !stockProducto.getText().isEmpty() && !precioProducto.getText().isEmpty()
                && !tipo.isEmpty() && !estado.isEmpty() && !idCorreo.getText().isEmpty()) {

                    int stock = Integer.parseInt(stockProducto.getText());
                    double precio = Double.parseDouble(precioProducto.getText());
                    Optional<Usuario> idUsuario = usuarioService.idUsuario(idUsuarioProducto);

                    ProductoDto productoDto = new ProductoDto();
                    productoDto.setNombreProducto(nombre);
                    productoDto.setTipoProducto(tipo);
                    productoDto.setEstadoProducto(estado);
                    productoDto.setUsuario(idUsuario.orElse(null));

                    Producto producto=productoService.guardarProducto(productoDto);


                } else {
                    alertaCasillasVacias();
                }

            } else {
                alertaUsuarioExistente();
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }


    }

}
