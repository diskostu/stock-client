package de.diskostu.reactivespringboot.stockui;

import de.diskostu.reactivespringboot.stockclient.WebClientStockClient;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import org.springframework.stereotype.Component;

@Component
public class ChartController {

    @FXML
    public LineChart<String, Double> chart;
    private WebClientStockClient webClientStockClient;

    public ChartController(WebClientStockClient webClientStockClient) {
        this.webClientStockClient = webClientStockClient;
    }
}