package com.java.ventaCoffe.view.controller.minventario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class mostrarComboController {

    private String[] tipoProductoA = {"Bebida", "Enlatado", "postre", "bocadito"};

    private String[] estadoProductoA = {"Disponible", "No Disponible"};


    public void recorrerTipoProducto(ComboBox comboTipoProducto) {

        List<String> list = new ArrayList<>();

        for (String tipo : tipoProductoA) {
            list.add(tipo);
        }
        ObservableList lisData = FXCollections.observableArrayList(list);
        comboTipoProducto.setItems(lisData);
    }

    public void recorrerEstadoProducto(ComboBox comboEstadoProducto) {
        List<String> list = new ArrayList<>();

        for (String tipo : estadoProductoA) {
            list.add(tipo);
        }
        ObservableList lisData = FXCollections.observableArrayList(list);
        comboEstadoProducto.setItems(lisData);
    }

}
