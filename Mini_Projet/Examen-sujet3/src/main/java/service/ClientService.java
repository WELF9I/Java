package service;

import dao.ClientDAO;
import model.Client;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClientService {
    private ClientDAO clientDAO;

    public ClientService() {
        this.clientDAO = new ClientDAO();
    }

    public List<Client> getAllClients() {
        return clientDAO.getAllClients();
    }

    public void insertClient(Client client) {
        clientDAO.insertClient(client);
    }

    public Client getClientById(int clientId) {
        return clientDAO.getClientById(clientId);
    }

    public List<Client> searchClientsByName(String name) {
        return clientDAO.searchClientsByName(name);
    }

    public int getClientIdByName(String selectedClientName) {
        return clientDAO.getClientIdByName(selectedClientName);
    }
}
