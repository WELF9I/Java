package ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class MainController {

    @FXML
    void handleAjouterReparation(ActionEvent event) {
        loadUI("AjouterReparation");
    }

    @FXML
    void handleRechercherClient(ActionEvent event) {
        loadUI("RechercherClient");
    }

    @FXML
    void handleAfficherFacture(ActionEvent event) {
        loadUI("AfficherFacture");
    }
    @FXML
    void handleAjouterClient(ActionEvent event) {
        loadUI("AjouterClient");
    }

    @FXML
    private void handleAjouterCategorie(ActionEvent event) {
        loadUI("AjouterCategorie");
    }

    private void loadUI(String ui) {
        try {
            String resourcePath = "/org/example/examensujet/" + ui + ".fxml";
            System.out.println("Loading FXML from: " + resourcePath);
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resourcePath));
            if (loader.getLocation() == null) {
                System.out.println("FXML file not found at: " + resourcePath);
                return;
            }
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
