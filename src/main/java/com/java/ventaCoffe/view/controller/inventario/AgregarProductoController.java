package com.java.ventaCoffe.view.controller.inventario;

import com.java.ventaCoffe.controller.impl.ProductoServiceImpl;
import com.java.ventaCoffe.controller.impl.UsuarioServiceImpl;
import com.java.ventaCoffe.model.dto.ProductoDto;
import com.java.ventaCoffe.model.entity.Producto;
import com.java.ventaCoffe.model.entity.Usuario;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;


@Component
public class AgregarProductoController {

    private final Logger logger = LoggerFactory.getLogger(AgregarProductoController.class);

    @Autowired
    private ProductoServiceImpl productoService;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private ImagenProductoController imagenProductoController;

    @Autowired
    private mostrarComboController comboController;

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

    public void limpiarCasillas(TextField nombreProducto,
                                TextField stockProducto,
                                TextField precioProducto){

        nombreProducto.setText("");
        stockProducto.setText("");
        precioProducto.setText("");
    }

    public void productoGuardado(TextField nombreProducto,TextField StockProducto, TextField precioProducto){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setTitle("Producto Guardado");
        alerta.setContentText("El producto fue guardado con exito en el inventario");
        alerta.showAndWait();

        nombreProducto.setText("");
        StockProducto.setText("");
        precioProducto.setText("");
    }

    public void fijarlogitudMaximo(final TextField campoTexto, final int tamañoMáximo) {
        campoTexto.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number valorAnterior, Number valorActual) {
                if (valorActual.intValue() > valorAnterior.intValue()) {
                    // Verificar si el nuevo texto contiene caracteres no numéricos
                    if (!campoTexto.getText().matches("[0-9]*")) {
                        // Si contiene caracteres no numéricos, eliminarlos
                        campoTexto.setText(campoTexto.getText().replaceAll("[^0-9]", ""));
                        return;
                    }

                    // Revisar que la longitud del texto no sea mayor a la variable definida.
                    if (campoTexto.getText().length() >= tamañoMáximo) {
                        campoTexto.setText(campoTexto.getText().substring(0, tamañoMáximo));
                    }
                }
            }
        });
    }

    @Autowired
    private ResourceLoader resourceLoader;

    public void agregarProducto(TextField nombreProducto,
                                TextField stockProducto,
                                TextField precioProducto,
                                String idCorreo,
                                ComboBox tipoProducto,
                                ComboBox estadoProducto,String rutaImagen)
    {
        try {



            String nombre = nombreProducto.getText();
            String tipo = (String) tipoProducto.getSelectionModel().getSelectedItem();
            String estado = (String) estadoProducto.getSelectionModel().getSelectedItem();
            String usuario = idCorreo;
            Optional<Producto> productoExistente = productoService.findByNombreProducto(nombre);
            Optional<Usuario> usuarioProducto = usuarioService.findByCorreoUsuario(usuario);
            int idUsuarioProducto = usuarioProducto.get().getIdUsuario();

            logger.info("Prueba: {}, User: {}", nombre,usuario);
            String prueba="";
            if(rutaImagen==null){

                Resource imagenUrl = resourceLoader.getResource("classpath:imageProducto/default.jpg");
                if(imagenUrl.exists()){
                    URL photoUrl = imagenUrl.getURL();
                    prueba = photoUrl.getPath();
                    logger.info("Ruta: {}",prueba);
                }else {
                    prueba="Imagen no encontrada, posible problema de cache";
                    logger.info("Ruta: {}",prueba);
                }

            }else {
                prueba=rutaImagen;
            }

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
                    productoDto.setImagenProducto(prueba);
                    productoDto.setUsuario(idUsuario.orElse(null));
                    Producto producto = productoService.guardarProducto(productoDto);
                    productoGuardado(nombreProducto,stockProducto,precioProducto);
                    comboController.recorrerTipoProducto(tipoProducto);
                    comboController.recorrerEstadoProducto(estadoProducto);



                } else {
                    alertaCasillasVacias();
                    limpiarCasillas(nombreProducto,stockProducto,precioProducto);
                }

            } else {
                alertaUsuarioExistente();
                limpiarCasillas(nombreProducto,stockProducto,precioProducto);
            }

        }
        catch (IOException exception){
            System.out.println("Error: "+exception.getMessage());
        }
        catch (RuntimeException e) {
            limpiarCasillas(nombreProducto,stockProducto,precioProducto);
            System.out.println("Error: " + e.getMessage());

        }

    }

}