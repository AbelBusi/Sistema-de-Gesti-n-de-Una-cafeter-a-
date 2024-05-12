package com.java.ventaCoffe.view.controller.error;


import javafx.scene.control.Alert;

public class Errores {

    public void mostrarErrorAplicacion (){

        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setHeaderText(null);
        alerta.setTitle("Error de ejecucion");
        alerta.setContentText("Posiblemente no se encuentra la ubicacion del fxml");
        alerta.showAndWait();
    }

    public void errorDatos (){

        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setHeaderText(null);
        alerta.setTitle("Error de ejecucion");
        alerta.setContentText("Problemas con la base de datos. Intentalo denuevo mas tarde");
        alerta.showAndWait();
    }



}
