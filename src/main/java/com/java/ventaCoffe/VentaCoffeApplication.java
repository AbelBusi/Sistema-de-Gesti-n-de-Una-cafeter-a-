package com.java.ventaCoffe;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class VentaCoffeApplication extends Application {

	public static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		launch();
		SpringApplication.run(VentaCoffeApplication.class, args);
	}

	@Override
	public void start(Stage stage) throws Exception {

	}
}
