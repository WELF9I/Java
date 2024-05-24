package org.example.examensujet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/examensujet/Main.fxml"));
        primaryStage.setTitle("Ajouter Client");
        primaryStage.setScene(new Scene(root, 900, 900));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}