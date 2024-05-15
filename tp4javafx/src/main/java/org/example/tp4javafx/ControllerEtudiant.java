package org.example.tp4javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.tp4javafx.service.EtudiantM;
import org.example.tp4javafx.modele.Etudiant;

import java.util.Arrays;
import java.util.List;

public class ControllerEtudiant {

    @FXML
    private TextField txtPrenom;

    @FXML
    private Button btnModifier;

    @FXML
    private Button btnAjouter;

    @FXML
    private RadioButton txtSexe;

    @FXML
    private TextField txtNom;

    @FXML
    private ChoiceBox<String> txtFiliere;

    @FXML
    private Button btnSupprimer;

    @FXML
    private TableView<Etudiant> tableView;



    private EtudiantM etudiantM;

    public void initialize() {
        etudiantM = new EtudiantM();
        populateChoiceBox();
        populateTableView();
    }

    private void populateChoiceBox() {
        List<String> filieres = Arrays.asList("DSI22", "DSI21", "MDW21", "MDW22");
        txtFiliere.getItems().addAll(filieres);
    }


    private void populateTableView() {
        List<Etudiant> etudiants = etudiantM.getAll();
        tableView.getItems().addAll(etudiants);
    }


    @FXML
    void ajouterEtudiant(ActionEvent event) {
        String nom = txtNom.getText();
        String prenom = txtPrenom.getText();
        String sexe = txtSexe.isSelected() ? "M" : "F";
        String filiere = txtFiliere.getValue();
        Etudiant etudiant = new Etudiant(nom, prenom, sexe, filiere);
        etudiantM.create(etudiant);
        tableView.getItems().add(etudiant);
    }

    @FXML
    void supprimerEtudiant(ActionEvent event) {

    }

}
