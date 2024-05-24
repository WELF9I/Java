package ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Client;
import service.ClientService;

import java.util.List;

public class RechercherClientController {

    @FXML
    private Label selectedClientAddressLabel;

    @FXML
    private Label selectedClientPhoneLabel;

    @FXML
    private Label selectedClientNameLabel;

    @FXML
    private TextField clientNameField;

    @FXML
    private ListView<Client> clientListView;

    private final ClientService clientService;

    public RechercherClientController() {
        this.clientService = new ClientService();
    }

    @FXML
    void handleSearchAction(ActionEvent event) {
        String name = clientNameField.getText();
        List<Client> clients = clientService.searchClientsByName(name);
        System.out.println("Clients: " + clients);
        ObservableList<Client> clientObservableList = FXCollections.observableArrayList(clients);
        clientListView.setItems(clientObservableList);
    }

    public void initialize() {
        clientListView.setCellFactory(param -> new ListCell<Client>() {
            @Override
            protected void updateItem(Client client, boolean empty) {
                super.updateItem(client, empty);

                if (empty || client == null) {
                    setText(null);
                } else {
                    setText(client.getNom());
                }
            }
        });
    }

    @FXML
    void handleClientSelectAction(MouseEvent event) { // Use MouseEvent
        Client selectedClient = clientListView.getSelectionModel().getSelectedItem();
        if (selectedClient != null) {
            System.out.println("Client selected: " + selectedClient.getNom());
            selectedClientNameLabel.setText(selectedClient.getNom());
            selectedClientAddressLabel.setText(selectedClient.getAdresse());
            selectedClientPhoneLabel.setText(selectedClient.getNumeroTelephone());
        } else {
            System.out.println("No client selected.");
        }
    }

}
