package org.example.tp4javafx.service;

import org.example.tp4javafx.connexion.Connexion;
import org.example.tp4javafx.modele.Etudiant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtudiantM {
    public boolean create(Etudiant etudiant) {
        try (Connection connection = Connexion.getCn();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Etudiant (nom, prenom, sexe, filiere) VALUES (?, ?, ?, ?)")) {
            statement.setString(1, etudiant.getNom());
            statement.setString(2, etudiant.getPrenom());
            statement.setString(3, etudiant.getSexe());
            statement.setString(4, etudiant.getFiliere());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Etudiant etudiant) {
        try (Connection connection = Connexion.getCn();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Etudiant WHERE id = ?")) {
            statement.setInt(1, etudiant.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Etudiant etudiant) {
        try (Connection connection = Connexion.getCn();
             PreparedStatement statement = connection.prepareStatement("UPDATE Etudiant SET nom = ?, prenom = ?, sexe = ?, filiere = ? WHERE id = ?")) {
            statement.setString(1, etudiant.getNom());
            statement.setString(2, etudiant.getPrenom());
            statement.setString(3, etudiant.getSexe());
            statement.setString(4, etudiant.getFiliere());
            statement.setInt(5, etudiant.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Etudiant findById(int id) {
        try (Connection connection = Connexion.getCn();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Etudiant WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Etudiant(resultSet.getInt("id"), resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("sexe"), resultSet.getString("filiere"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Etudiant> findAll() {
        List<Etudiant> etudiants = new ArrayList<>();
        try (Connection connection = Connexion.getCn();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Etudiant")) {
            while (resultSet.next()) {
                etudiants.add(new Etudiant(resultSet.getInt("id"), resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("sexe"), resultSet.getString("filiere")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiants;
    }


    public List<Etudiant> getAll() {
        return findAll();
    }

}
