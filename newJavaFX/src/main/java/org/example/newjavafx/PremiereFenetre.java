package org.example.newjavafx;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
public class PremiereFenetre extends Application {
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 400, 400, Color.WHITE);
        Line ligne = new Line(150, 50, 300, 50);
        ligne.setStroke(Color.BLUE);
        Rectangle rectangle = new Rectangle(100, 100, 150, 100);
        rectangle.setFill(Color.GREEN);
        Circle cercle = new Circle(300, 300, 50);
        cercle.setFill(Color.RED);
        root.getChildren().addAll(ligne, rectangle, cercle);
        primaryStage.setTitle("Ma première fenêtre");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }}
