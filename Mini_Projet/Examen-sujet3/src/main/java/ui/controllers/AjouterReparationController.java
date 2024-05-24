package ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.*;
import service.*;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AjouterReparationController {
    private static final String SELECT_CLIENT_BY_ID = "SELECT * FROM Client WHERE id_client = ?";
    private static final String SELECT_CATEGORIE_BY_ID = "SELECT * FROM CategoriesAppareil WHERE id = ?";

    @FXML
    private TextField labourHoursField;

    @FXML
    private TextField deviceBrandField;

    @FXML
    private ComboBox<CategorieAppareil> categoryComboBox;

    @FXML
    private ComboBox<Client> clientComboBox;

    @FXML
    private ListView<PieceDetachee> partsListView;

    @FXML
    private TextField deviceDescriptionField;

    private ClientService clientService;
    private ReparationService reparationService;

    public AjouterReparationController() {
        this.clientService = new ClientService();
        this.reparationService = new ReparationService();
    }

    @FXML
    void initialize() {
        // Set up a cell factory to convert Client objects to strings
        clientComboBox.setCellFactory(param -> new ListCell<Client>() {
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

        clientComboBox.getItems().addAll(clientService.getAllClients());

        categoryComboBox.setCellFactory(param -> new ListCell<CategorieAppareil>() {
            @Override
            protected void updateItem(CategorieAppareil category, boolean empty) {
                super.updateItem(category, empty);
                if (empty || category == null) {
                    setText(null);
                } else {
                    setText(category.getLibelle());}
            }
        });

        categoryComboBox.getItems().addAll(reparationService.getAllCategories());

        partsListView.setCellFactory(param -> new ListCell<PieceDetachee>() {
            @Override
            protected void updateItem(PieceDetachee piece, boolean empty) {
                super.updateItem(piece, empty);
                if (empty || piece == null) {
                    setText(null);
                } else {
                    setText(piece.getNom());
                }
            }
        });

        partsListView.getItems().addAll(reparationService.getAllPiecesDetachees());
    }

    @FXML
    void handleSubmitAction(ActionEvent event) {
        try {
            String deviceDescription = deviceDescriptionField.getText();
            String deviceBrand = deviceBrandField.getText();
            Client selectedClient = clientComboBox.getValue();
            CategorieAppareil selectedCategory = categoryComboBox.getValue();
            int labourHours = Integer.parseInt(labourHoursField.getText());

            Appareil newAppareil = new Appareil(0, selectedClient, selectedCategory, deviceDescription, deviceBrand);
            reparationService.insertAppareil(newAppareil);
            List<PieceAChanger> piecesAChanger = new ArrayList<>();
            for (PieceDetachee piece : partsListView.getItems()) {
                PieceAChanger pieceAChanger = new PieceAChanger(0, null, piece, 1);
                piecesAChanger.add(pieceAChanger);
            }

            OrdreReparation newOrdreReparation = new OrdreReparation(0, newAppareil, labourHours, piecesAChanger);
            reparationService.insertOrdreReparation(newOrdreReparation);
            for (PieceAChanger pieceAChanger : piecesAChanger) {
                pieceAChanger.setOrdreReparation(newOrdreReparation);
                reparationService.insertPieceAChanger(pieceAChanger);
            }
            clearForm();
        } catch (NumberFormatException e) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private Client getClientById(int clientId) {
        Client client = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(SELECT_CLIENT_BY_ID)) {
            statement.setInt(1, clientId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("ID_Client");
                    String nom = resultSet.getString("Nom");
                    String adresse = resultSet.getString("Adresse");
                    String numeroTelephone = resultSet.getString("NumeroTelephone");

                    client = new Client(id, nom, adresse, numeroTelephone);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    private CategorieAppareil getCategorieAppareilById(int categorieId) {
        CategorieAppareil categorie = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(SELECT_CATEGORIE_BY_ID)) {
            statement.setInt(1, categorieId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("ID_Categorie");
                    String libelle = resultSet.getString("Libelle");
                    double tarif = resultSet.getDouble("Tarif");

                    categorie = new CategorieAppareil(id, libelle, tarif);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorie;
    }


    private void clearForm() {
        deviceDescriptionField.clear();
        deviceBrandField.clear();
        labourHoursField.clear();
        clientComboBox.setValue(null);
        categoryComboBox.setValue(null);
        partsListView.getSelectionModel().clearSelection();
    }
}
