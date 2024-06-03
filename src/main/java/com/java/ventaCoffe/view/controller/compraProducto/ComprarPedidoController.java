package com.java.ventaCoffe.view.controller.compraProducto;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Service;

@Service
public class ComprarPedidoController {
    public Double pagar(TextField monto, Double totalPagar, Label cambio) {

        try {
            Double pagar = totalPagar;

            if (!(monto.getText().isEmpty())) {
                if (!(pagar == null) && !(pagar == 0.0)) {

                    Double montoIngresado = Double.parseDouble(monto.getText());
                    if (montoIngresado >= pagar) {
                        System.out.println("Monto pagado se le dara su cambio");

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Monto a pagar");
                        alert.setContentText("Monto recibido y cambiado correctamente. ");
                        alert.showAndWait();
                        monto.setEditable(false);
                        cambio.setText("$" + (montoIngresado - pagar));
                        return montoIngresado - pagar;
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("Monto a pagar");
                        alert.setContentText("Por favor, El monto ingresado es insuficiente para realizar su pago!. ");
                        alert.showAndWait();
                        cambio.setText("$0.0");
                        return 0.0;
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Monto a pagar");
                    alert.setContentText("Por favor, el monto no debe ser $0.0!. ");
                    alert.showAndWait();
                    cambio.setText("$0.0");
                    return 0.0;
                }
            } else {
                System.out.println("Ingrese un valor exacto ");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Monto a pagar");
                alert.setContentText("Por favor, ingresa un monto!. ");
                alert.showAndWait();
                cambio.setText("$0.0");
                cambio.setText("$0.0");
                return 0.0;
            }
        }catch (Exception exception){
            System.out.println("Error: "+exception.getMessage());
            cambio.setText("$0.0");

            return 0.0;

        }

    }


}
