package ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import dao.ClientDAO;
import model.Client;

public class AjouterClientController {

    @FXML
    private TextField numeroTelephoneField;

    @FXML
    private TextField adresseField;

    @FXML
    private TextField nomField;

    @FXML
    private void handleSubmitAction() {
        String nom = nomField.getText();
        String adresse = adresseField.getText();
        String numeroTelephone = numeroTelephoneField.getText();
        Client newClient = new Client(nom, adresse, numeroTelephone);
        ClientDAO clientDAO = new ClientDAO();
        clientDAO.insertClient(newClient);
    }
}
