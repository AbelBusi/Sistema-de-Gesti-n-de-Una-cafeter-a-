package com.java.ventaCoffe.view.controller.inventario;

import com.java.ventaCoffe.controller.impl.ProductoServiceImpl;
import com.java.ventaCoffe.model.entity.Producto;
import com.java.ventaCoffe.view.controller.ControllerMenu;
import com.java.ventaCoffe.view.controller.error.Errores;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ActualizarProductoContoller {

    private final Logger loggger = LoggerFactory.getLogger(ActualizarProductoContoller.class);

    Errores errores = new Errores();

    @Autowired
    private ProductoServiceImpl productoService;

    public Integer ObtenerTable(TableView<Producto> tableView,TextField idProducto, TextField nombreProducto, TextField stockProducto,
                             TextField precioProducto){
        Producto producto = tableView.getSelectionModel().getSelectedItem();
        int num = tableView.getSelectionModel().getSelectedIndex();
        if ((num -1)<-1)
        System.out.println(producto.getIdProducto());
        idProducto.setText(String.valueOf(producto.getIdProducto()));
        nombreProducto.setText(producto.getNombreProducto());
        stockProducto.setText(String.valueOf(producto.getStockProducto()));
        precioProducto.setText(String.valueOf(producto.getPrecioProducto()));
        Integer id=producto.getIdProducto();
        return id;

    }

    public void limpiarCasillas (TextField idProducto,
                                 TextField stockProducto,
                                 TextField precioProducto,
                                 TextField nombreProducto){
        idProducto.setText("");
        stockProducto.setText("");
        precioProducto.setText("");
        nombreProducto.setText("");

    }

    public void ActualizarProducto(TextField txtIdProducto,Integer idProducto, TextField txtNombreProducto,
                                   TextField txtStockProducto, TextField txtPrecioProducto,
                                   ComboBox CestadoProducto, ComboBox CtipoProducto){

        Optional<Producto> obtenerIdProducto = productoService.findById(idProducto);
        String nombreProducto = txtNombreProducto.getText();
        String estadoProducto =(String) CestadoProducto.getSelectionModel().getSelectedItem();
        String tipoProducto = (String) CtipoProducto.getSelectionModel().getSelectedItem();
        try{
        if(obtenerIdProducto.isPresent()){

            loggger.info("Usuario Encontrado id: {}",obtenerIdProducto);

            if(!nombreProducto.isEmpty() && !txtStockProducto.getText().isEmpty() &&
            !txtPrecioProducto.getText().isEmpty() && !estadoProducto.isEmpty()
            && !tipoProducto.isEmpty() ){

                int stockProducto = Integer.parseInt(txtStockProducto.getText().toString());
                double precioProducto = Double.parseDouble(txtPrecioProducto.getText());

                obtenerIdProducto.get().setNombreProducto(nombreProducto);
                obtenerIdProducto.get().setStockProducto(stockProducto);
                obtenerIdProducto.get().setPrecioProducto(precioProducto);
                obtenerIdProducto.get().setEstadoProducto(estadoProducto);
                obtenerIdProducto.get().setTipoProducto(tipoProducto);
                productoService.actualizarProducto(obtenerIdProducto.orElse(null));

                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setHeaderText(null);
                alerta.setTitle("Gestion de productos");
                alerta.setContentText("Se actualizaron los datos de manera correcta");
                alerta.showAndWait();

                limpiarCasillas(txtIdProducto,txtStockProducto,txtPrecioProducto,txtNombreProducto);


            }else {

                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setHeaderText(null);
                alerta.setTitle("Gestion de productos");
                alerta.setContentText("No puede agregar vacios a los productos");
                alerta.showAndWait();
                limpiarCasillas(txtIdProducto,txtStockProducto,txtPrecioProducto,txtNombreProducto);


            }

        }else{
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText(null);
            alerta.setTitle("Gestion de productos");
            alerta.setContentText("No existe el producto y no puede ser actualizado");
            alerta.showAndWait();
            limpiarCasillas(txtIdProducto,txtStockProducto,txtPrecioProducto,txtNombreProducto);

        }
        }catch (Exception exception){
            System.out.println("Error: "+exception.getMessage());
            errores.errorDatos();
            limpiarCasillas(txtIdProducto,txtStockProducto,txtPrecioProducto,txtNombreProducto);

        }

    }


}
