package com.java.ventaCoffe.view.controller.graficos;

import com.java.ventaCoffe.controller.impl.PedidoServiceImpl;
import com.java.ventaCoffe.model.dto.PedidoDtoProjection;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
public class VentasDiariasController {

    @Autowired
    private PedidoServiceImpl pedidoService;

    public void mostrarGraficaVentaDiaria(BarChart<String, Number> dhasboardVentasDiarias) {
        dhasboardVentasDiarias.getData().clear();
        try {
            List<PedidoDtoProjection> pedidos = pedidoService.pedidoDia();

            XYChart.Series<String, Number> chart = new XYChart.Series<>();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            for (PedidoDtoProjection p : pedidos) {
                Date fecha = p.getDia();
                Double totalPedido = p.getTotalPedido();
                if (totalPedido != null) {
                    String formattedDate = dateFormat.format(fecha);
                    chart.getData().add(new XYChart.Data<>(formattedDate, totalPedido));
                }
            }
            dhasboardVentasDiarias.getData().add(chart);

        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

}
