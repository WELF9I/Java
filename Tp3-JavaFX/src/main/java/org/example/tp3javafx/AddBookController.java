package org.example.tp3javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {
    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfFirstName;
    @FXML
    private TextField tfEmail;
    @FXML
    private Button addBtn;
    @FXML
    private Button exportBtn;
    @FXML
    private Button importBtn;
    @FXML
    private Button removeBtn;
    @FXML
    private Button quitBtn;
    @FXML
    private TableView<Person> table;
    @FXML
    private TableColumn<Person, String> emailCol;
    @FXML
    private TableColumn<Person, String> firstNameCol;
    @FXML
    private TableColumn<Person, String> lastNameCol;

    private DataClass data;


    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        data = new DataClass();

        emailCol.setCellValueFactory(new PropertyValueFactory<>("adresseEmail"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("nom"));

        table.getColumns().addAll(emailCol, firstNameCol, lastNameCol);

        ObservableList<Person> observableList = FXCollections.observableList(data.getImportList());
        table.setItems(observableList);
    }

    @FXML
    private void handleAddButtonAction(ActionEvent event) {
        String firstName = tfFirstName.getText();
        String lastName = tfLastName.getText();
        String email = tfEmail.getText();

        Person newPerson = new Person(firstName, lastName, email);
        table.getItems().add(newPerson);

        tfFirstName.clear();
        tfLastName.clear();
        tfEmail.clear();
    }


    @FXML
    private void handleExportButtonAction(ActionEvent event) {
        ObservableList<Person> selectedPersons = table.getSelectionModel().getSelectedItems();

        data.getExportList().addAll(selectedPersons);

        table.getItems().removeAll(selectedPersons);
    }

    @FXML
    private void handleImportButtonAction(ActionEvent event) {
        ObservableList<Person> selectedPersons = table.getSelectionModel().getSelectedItems();

        data.getImportList().addAll(selectedPersons);

        table.getItems().removeAll(selectedPersons);
    }

    @FXML
    private void handleRemoveButtonAction(ActionEvent event) {
        ObservableList<Person> selectedPersons = table.getSelectionModel().getSelectedItems();

        table.getItems().removeAll(selectedPersons);
    }

    @FXML
    private void handleQuitButtonAction() {
        quitBtn.getScene().getWindow().hide();
    }
    @SuppressWarnings("unused")
    private void showInvalidEmailAlert() {
        showAlert();
    }

    private void showAlert() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez entrer une adresse email valide.");
        alert.showAndWait();
    }

}