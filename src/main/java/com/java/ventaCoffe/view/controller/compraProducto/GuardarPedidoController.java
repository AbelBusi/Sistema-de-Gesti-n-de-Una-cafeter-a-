package com.java.ventaCoffe.view.controller.compraProducto;

import com.java.ventaCoffe.controller.impl.PedidoServiceImpl;
import com.java.ventaCoffe.controller.impl.UsuarioServiceImpl;
import com.java.ventaCoffe.model.entity.Pedido;
import com.java.ventaCoffe.model.entity.Usuario;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class GuardarPedidoController {

    private final Logger logger = LoggerFactory.getLogger(GuardarPedidoController.class);

    @Autowired
    private PedidoServiceImpl pedidoService;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    public void guardarPedido(Double totalPedido, String usuario, TextField montoUsuario) {

        Optional<Usuario> user = usuarioService.findByCorreoUsuario(usuario);

        try {

            if (user.isPresent()) {

                logger.info("El usuario existe: {}", user.get().getPreguntaUsuario());
                if (!(totalPedido <= 0.0)) {
                    if (!montoUsuario.getText().isEmpty()) {

                        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Error de compra");
                        alert.setContentText("Lo siento, debe ingresar el monto a pagar o seleccionar un pedido!");
                        Optional<ButtonType> buttonType=alert.showAndWait();
                        if(buttonType.get().equals(ButtonType.OK)){
                            Pedido pedido = new Pedido();
                            pedido.setFechaPedido(LocalDateTime.now());
                            pedido.setNumeroPedido(pedidoService.generarNumeroPedido());
                            pedido.setTotalPedido(totalPedido);
                            pedido.setUsuario(user.get());
                            pedidoService.guardarPedido(pedido);
                            logger.info("Guardando producto");
                        }

                    } else {

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("Error de compra");
                        alert.setContentText("Lo siento, debe ingresar el monto a pagar o seleccionar un pedido!");
                        alert.showAndWait();

                    }

                } else {
                    logger.info("No acepta valores 0");
                }

            } else {

                logger.info("El usuario no existe: {}", user);

            }

        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }

    }

}
