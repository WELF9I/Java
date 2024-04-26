package org.example.registrationform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;

public class RegistrationFormController {
    @FXML
    private Button submit;
    @FXML
    private PasswordField password;

    @FXML
    private TextField name;

    @FXML
    private TextField email;

    @FXML
    private Label welcomeText;


    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        Window owner = name.getScene().getWindow();

        if (name.getText().isEmpty() || email.getText().isEmpty() || password.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Form Error!");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields");
            alert.initOwner(owner);
            alert.show();
        } else {
            Alert alert2 = new Alert(AlertType.INFORMATION);
            alert2.setTitle("Registration Success!");
            alert2.setHeaderText(null);
            alert2.setContentText("Welcome");
            alert2.initOwner(owner);
            alert2.show();
        }
    }

}
