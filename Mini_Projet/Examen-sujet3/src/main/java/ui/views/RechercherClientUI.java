package ui.views;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import ui.controllers.RechercherClientController;

import java.io.IOException;

public class RechercherClientUI {

    public void display() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/views/RechercherClient.fxml"));
        Parent root = loader.load();

        RechercherClientController controller = loader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Rechercher Client");
        stage.show();
    }
}
