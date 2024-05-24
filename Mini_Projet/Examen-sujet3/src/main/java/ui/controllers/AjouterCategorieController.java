package ui.controllers;

import dao.CategorieAppareilDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.CategorieAppareil;

import java.sql.SQLException;

public class AjouterCategorieController {

    @FXML
    private TextField libelleField;

    @FXML
    private TextField tarifField;

    @FXML
    private void handleSubmitAction() {
        String libelle = libelleField.getText();
        double tarif = Double.parseDouble(tarifField.getText());

        CategorieAppareil categorie = new CategorieAppareil(libelle, tarif);
        CategorieAppareilDAO categorieDAO = new CategorieAppareilDAO();

        try {
            categorieDAO.insertCategorie(categorie);
            System.out.println("Categorie ajoutée avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la catégorie : " + e.getMessage());
        }
    }
}
