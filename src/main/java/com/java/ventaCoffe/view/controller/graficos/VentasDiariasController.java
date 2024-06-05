package com.java.ventaCoffe.view.controller.graficos;

import com.java.ventaCoffe.controller.impl.PedidoServiceImpl;
import javafx.scene.chart.XYChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VentasDiariasController {

    @Autowired
    private PedidoServiceImpl pedidoService;

    public void mostrarGraficaVentaDiaria(){

        XYChart.Series chart = new XYChart.Series<>();

    }

}
