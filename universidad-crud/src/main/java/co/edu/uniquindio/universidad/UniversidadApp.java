package co.edu.uniquindio.universidad;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal que inicia la aplicación JavaFX.
 * Punto de entrada de la aplicación de gestión universitaria.
 */
public class UniversidadApp extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UniversidadApp.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 920, 650);
        stage.setTitle("Sistema de Gestión Universitaria - CRUD");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

