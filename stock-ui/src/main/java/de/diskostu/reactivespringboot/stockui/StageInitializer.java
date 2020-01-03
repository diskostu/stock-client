package de.diskostu.reactivespringboot.stockui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StageInitializer implements ApplicationListener<ChartApplication.StageReadyEvent> {
    private final int width = 800;
    private final int height = 600;

    @Value("classpath:chart.fxml")
    private Resource chartResource;
    private String applicationTitle;
    private ApplicationContext applicationContext;


    public StageInitializer(@Value("${spring.application.ui.title}") String applicationTitle,
                            ApplicationContext applicationContext) {
        this.applicationTitle = applicationTitle;
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ChartApplication.StageReadyEvent event) {
        try {
            final FXMLLoader fxmlLoader = new FXMLLoader(chartResource.getURL());
            fxmlLoader.setControllerFactory(aClass -> applicationContext.getBean(aClass));
            Parent parent = fxmlLoader.load();

            final Stage stage = event.getStage();
            stage.setScene(new Scene(parent, width, height));
            stage.setTitle(applicationTitle);
            stage.setMinWidth(width);
            stage.setMinHeight(height);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}