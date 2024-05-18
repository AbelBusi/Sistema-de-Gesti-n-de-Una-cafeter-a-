package com.java.ventaCoffe.view.controller.inventario;

import com.java.ventaCoffe.controller.impl.ProductoServiceImpl;
import com.java.ventaCoffe.model.entity.Producto;
import com.java.ventaCoffe.view.controller.error.Errores;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Optional;

@Component
public class ActualizarProductoContoller {

    private final Logger loggger = LoggerFactory.getLogger(ActualizarProductoContoller.class);

    Errores errores = new Errores();

    @Autowired
    private ProductoServiceImpl productoService;

    @Autowired
    private ResourceLoader resourceLoader;


    public String path;
    public String normal ;

    public Integer ObtenerTable(TableView<Producto> tableView, TextField idProducto, TextField nombreProducto, TextField stockProducto,
                                        TextField precioProducto, ImageView imagenProducto) {
        try {
            Producto producto = tableView.getSelectionModel().getSelectedItem();
            int num = tableView.getSelectionModel().getSelectedIndex();
            if ((num - 1) < -1)
                System.out.println(producto.getIdProducto());
            idProducto.setText(String.valueOf(producto.getIdProducto()));
            nombreProducto.setText(producto.getNombreProducto());
            stockProducto.setText(String.valueOf(producto.getStockProducto()));
            precioProducto.setText(String.valueOf(producto.getPrecioProducto()));
            path = "File:" + producto.getImagenProducto();
            normal=producto.getImagenProducto();
            loggger.info(" enviada por tableView: {}",normal);
            loggger.info("Imagen ruta: {}", path);
            try {
                Image image = new Image(path, 137, 135, false, true);
                imagenProducto.setImage(image);
            } catch (Exception e) {
                loggger.error("Error al cargar la imagen: {}", path, e);
                imagenProducto.setImage(null);
            }
            Integer id = producto.getIdProducto();
            return id;
        }catch (NullPointerException e){
            System.out.println("Error: "+e.getMessage());
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de tipado");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar una casilla de la tabla");
            alert.showAndWait();
            return null;
        }

    }

    public void limpiarCasillas(TextField idProducto,
                                TextField stockProducto,
                                TextField precioProducto,
                                TextField nombreProducto, Integer id,
                                ImageView imageView) {
        idProducto.setText("");
        stockProducto.setText("");
        precioProducto.setText("");
        nombreProducto.setText("");
        imageView.setImage(null);
        id=null;

    }

    public void ActualizarProducto(TextField txtIdProducto, Integer idProducto, TextField txtNombreProducto,
                                   TextField txtStockProducto, TextField txtPrecioProducto,
                                   ComboBox CestadoProducto, ComboBox CtipoProducto,String ruta, ImageView imageView) {
        try {

            loggger.info("Prueba Imagen : {}",normal);
            loggger.info("Imagen agregada despues de crear el producto: ",ruta);
            String nombreProducto = txtNombreProducto.getText();
            String estadoProducto = (String) CestadoProducto.getSelectionModel().getSelectedItem();
            String tipoProducto = (String) CtipoProducto.getSelectionModel().getSelectedItem();

            //Imagen

            if (!(idProducto == null)) {
                Optional<Producto> obtenerIdProducto = productoService.findById(idProducto);

                if (obtenerIdProducto.isPresent()) {

                    loggger.info("Usuario Encontrado id: {}", obtenerIdProducto);

                    if (!nombreProducto.isEmpty() && !txtStockProducto.getText().isEmpty() &&
                            !txtPrecioProducto.getText().isEmpty() && !(CestadoProducto.getSelectionModel().getSelectedItem() == null)
                            && !(CtipoProducto.getSelectionModel().getSelectedItem() == null)) {

                        int stockProducto = Integer.parseInt(txtStockProducto.getText().toString());
                        double precioProducto = Double.parseDouble(txtPrecioProducto.getText());

                        obtenerIdProducto.get().setNombreProducto(nombreProducto);
                        obtenerIdProducto.get().setStockProducto(stockProducto);
                        obtenerIdProducto.get().setPrecioProducto(precioProducto);
                        obtenerIdProducto.get().setEstadoProducto(estadoProducto);
                        obtenerIdProducto.get().setTipoProducto(tipoProducto);
                        obtenerIdProducto.get().setImagenProducto(normal);
                        productoService.actualizarProducto(obtenerIdProducto.orElse(null));
                        idProducto = null;
                        normal=null;
                        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                        alerta.setHeaderText(null);
                        alerta.setTitle("Gestion de productos");
                        alerta.setContentText("Se actualizaron los datos de manera correcta");
                        alerta.showAndWait();

                        limpiarCasillas(txtIdProducto, txtStockProducto, txtPrecioProducto, txtNombreProducto,idProducto,imageView);


                    } else {

                        Alert alerta = new Alert(Alert.AlertType.ERROR);
                        alerta.setHeaderText(null);
                        alerta.setTitle("Gestion de productos");
                        alerta.setContentText("No puede agregar vacios a los productos");
                        alerta.showAndWait();
                        limpiarCasillas(txtIdProducto, txtStockProducto, txtPrecioProducto, txtNombreProducto,idProducto,imageView);


                    }

                } else {
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setHeaderText(null);
                    alerta.setTitle("Gestion de productos");
                    alerta.setContentText("No existe el producto y no puede ser actualizado");
                    alerta.showAndWait();
                    limpiarCasillas(txtIdProducto, txtStockProducto, txtPrecioProducto, txtNombreProducto,idProducto,imageView);

                }
            }else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setHeaderText(null);
                alerta.setTitle("Gestion de productos");
                alerta.setContentText("Debe seleccionar almenos un producto, para actualizarlo");
                alerta.showAndWait();
                limpiarCasillas(txtIdProducto, txtStockProducto, txtPrecioProducto, txtNombreProducto,idProducto,imageView);
            }
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
            errores.errorDatos();
            limpiarCasillas(txtIdProducto, txtStockProducto, txtPrecioProducto, txtNombreProducto,idProducto,imageView);

        }

    }


}
