package com.java.ventaCoffe.view.controller.inventario;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ImagenProductoController {

    @FXML
    private Image image;

    public static String usuario;

    public void agregarImagenProductos(AnchorPane menuForm,ImageView anchorImage,String path) {

        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open image File","*png","*jpg"));
        File file = openFile.showOpenDialog(menuForm.getScene().getWindow());

        if(file!=null){

            path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(),137,135,false,true);
            anchorImage.setImage(image);

        }

    }

}
