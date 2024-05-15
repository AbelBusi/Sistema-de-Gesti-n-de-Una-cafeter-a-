package com.java.ventaCoffe;

import com.java.ventaCoffe.view.controller.error.Errores;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class VentaCoffeApplication extends Application {

	public static ConfigurableApplicationContext context;

	Errores errores = new Errores();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		try{
		context= SpringApplication.run(VentaCoffeApplication.class);
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("/com/java/ventaCoffe/login.fxml"));
		fxml.setControllerFactory(context::getBean);
		Scene scene = new Scene(fxml.load());
		stage.setTitle("LOGIN");
		stage.setMinHeight(450);
		stage.setMinWidth(600);
		stage.setScene(scene);
		stage.show();
		}catch (RuntimeException exception){
			errores.mostrarErrorAplicacion();
			System.out.println("Error: "+exception.getMessage());
		}
	}
}
