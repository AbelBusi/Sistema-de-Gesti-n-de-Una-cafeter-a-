package com.java.ventaCoffe.view.controller.graficos;

import com.java.ventaCoffe.controller.impl.PedidoServiceImpl;
import com.java.ventaCoffe.model.entity.Pedido;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class VentasDiariasController {

    @Autowired
    private PedidoServiceImpl pedidoService;

    public void mostrarGraficaVentaDiaria(BarChart dhasboardVentasDiarias) {
        dhasboardVentasDiarias.getData().clear();
        try {
            List<Pedido> pedidos = pedidoService.pedidoDia();

            XYChart.Series chart = new XYChart.Series<>();

            for (Pedido p : pedidos) {

                LocalDateTime fecha = p.getFechaPedido();
                Double totalPedido = p.getTotalPedido();
                chart.getData().add(new XYChart.Data<>(fecha, totalPedido));

            }
            dhasboardVentasDiarias.getData().add(chart);

        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

}
