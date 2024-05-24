package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Client;
import util.DatabaseConnection;

public class ClientDAO {
    private static final String SELECT_ALL_CLIENTS = "SELECT * FROM Clients";
    private static final String SELECT_CLIENT_BY_ID = "SELECT * FROM Clients WHERE ID_Client = ?";
    private static final String SEARCH_CLIENTS_BY_NAME = "SELECT * FROM Clients WHERE Nom LIKE ?";
    private static final String INSERT_CLIENT_SQL = "INSERT INTO Clients (Nom, Adresse, NumeroTelephone) VALUES (?, ?, ?)";

    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(SELECT_ALL_CLIENTS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("ID_Client");
                String nom = resultSet.getString("Nom");
                String adresse = resultSet.getString("Adresse");
                String numeroTelephone = resultSet.getString("NumeroTelephone");
                clients.add(new Client(id, nom, adresse, numeroTelephone));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
    public int getClientIdByName(String name) {
        int clientId = -1;
        String sql = "SELECT ID_Client FROM Clients WHERE Nom = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                clientId = resultSet.getInt("ID_Client");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientId;
    }
    public Client getClientById(int clientId) {
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

    public List<Client> searchClientsByName(String name) {
        List<Client> clients = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn == null) {
                System.out.println("Failed to establish database connection.");
                return clients;
            }
            System.out.println("Database connected");

            String query = SEARCH_CLIENTS_BY_NAME;
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, "%" + name + "%");
            System.out.println("Executing query: " + statement.toString());

            try (ResultSet resultSet = statement.executeQuery()) {
                System.out.println("Query executed");
                while (resultSet.next()) {
                    System.out.println("Boucle while entred");
                    int id = resultSet.getInt("ID_Client");
                    String nom = resultSet.getString("Nom");
                    String adresse = resultSet.getString("Adresse");
                    String numeroTelephone = resultSet.getString("NumeroTelephone");
                    clients.add(new Client(id, nom, adresse, numeroTelephone));

                    System.out.println("Clients found: " + clients.size());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public void insertClient(Client client) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_CLIENT_SQL)) {
            pstmt.setString(1, client.getNom());
            pstmt.setString(2, client.getAdresse());
            pstmt.setString(3, client.getNumeroTelephone());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
