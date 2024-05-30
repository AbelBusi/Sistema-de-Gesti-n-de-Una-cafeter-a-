package com.java.ventaCoffe.view.controller.cartProducto;

import com.java.ventaCoffe.controller.impl.PedidoTempServiceImpl;
import com.java.ventaCoffe.model.entity.PedidoTemporal;
import com.java.ventaCoffe.model.entity.Producto;
import com.java.ventaCoffe.view.controller.ControllerCarritoProducto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TablePedidoContoller {

    @Autowired
    private PedidoTempServiceImpl pedidoTempService;

    private final Logger logger = LoggerFactory.getLogger(ControllerCarritoProducto.class);


    public void guardarPedidoTemporal(Producto producto){

        if(!(producto==null)){

            if (!(producto.getStockProducto()==0.0) && !(producto.getNombreProducto()==null)
            && !(producto.getStockProducto()==0)){

                logger.info("Accediendo a guardar el pedidoTemporal");

                PedidoTemporal pedidoTemporal = new PedidoTemporal();
                pedidoTemporal.setNombrePedidoTemp(producto.getNombreProducto());
                pedidoTemporal.setPrecioPedidoTemp(producto.getPrecioProducto());
                pedidoTemporal.setCantidadPedidoTemp(producto.getStockProducto());

                pedidoTempService.guardarPedidoTemp(pedidoTemporal);

                logger.info("Guardando el pedidoTemporal: {}",pedidoTemporal);

            }else {
                logger.info("No puede estar vacio las cosas {}", producto);
            }


        }else {

            logger.info("Ocurrio un problema: {}", producto);

        }


    }
























//    public void SeleecionarPedidoTable(TableView<Producto> productoTableView){
//
//        Producto p = productoTableView.getSelectionModel().getSelectedItem();
//        if(p==null){
//            System.out.println("Debe seleccionar un producto");
//        }else {
//
//            System.out.println("Nombre Producto: "+p.getNombreProducto());
//
//        }
//
//    }

}
