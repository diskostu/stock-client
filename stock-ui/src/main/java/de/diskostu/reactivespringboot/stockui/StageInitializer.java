package de.diskostu.reactivespringboot.stockui;

import javafx.stage.Stage;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StageInitializer implements ApplicationListener<ChartApplication.StageReadyEvent> {
    @Override
    public void onApplicationEvent(ChartApplication.StageReadyEvent event) {
        final Stage stage = event.getStage();
    }
}
