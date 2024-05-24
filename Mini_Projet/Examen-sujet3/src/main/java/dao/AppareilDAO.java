package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Appareil;
import model.Client;
import model.CategorieAppareil;
import util.DatabaseConnection;
import util.DatabaseConnection;

public class AppareilDAO {
    private static final String SELECT_ALL_APPAREILS = "SELECT * FROM Appareils";

    public void insertAppareil(Appareil appareil) {
        String sql = "INSERT INTO Appareils (ID_Client, ID_Categorie, Description, Marque) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, appareil.getClient().getId());
            preparedStatement.setInt(2, appareil.getCategorie().getId());
            preparedStatement.setString(3, appareil.getDescription());
            preparedStatement.setString(4, appareil.getMarque());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Appareil> getAllAppareils() {
        List<Appareil> appareils = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(SELECT_ALL_APPAREILS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("ID_Appareil");
                int clientId = resultSet.getInt("ID_Client");
                int categorieId = resultSet.getInt("ID_Categorie");
                String description = resultSet.getString("Description");
                String marque = resultSet.getString("Marque");
                Client client = getClientById(clientId); // You should implement this method
                CategorieAppareil categorie = getCategorieById(categorieId); // You should implement this method
                appareils.add(new Appareil(id, client, categorie, description, marque));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appareils;
    }

    private Client getClientById(int clientId) {
        Client client = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM Clients WHERE ID_Client = ?");
        ) {
            statement.setInt(1, clientId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nom = resultSet.getString("Nom");
                String adresse = resultSet.getString("Adresse");
                String numeroTelephone = resultSet.getString("NumeroTelephone");
                client = new Client(clientId, nom, adresse, numeroTelephone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    private CategorieAppareil getCategorieById(int categorieId) {
        CategorieAppareil categorie = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM CategoriesAppareils WHERE ID_Categorie = ?");
        ) {
            statement.setInt(1, categorieId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String libelle = resultSet.getString("Libelle");
                double tarif = resultSet.getDouble("Tarif");
                categorie = new CategorieAppareil(categorieId, libelle, tarif);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorie;
    }

}
