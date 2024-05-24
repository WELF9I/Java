package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.CategorieAppareil;
import util.DatabaseConnection;

public class CategorieAppareilDAO {
    private static final String INSERT_CATEGORIE = "INSERT INTO CategoriesAppareils (Libelle, Tarif) VALUES (?, ?)";
    private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM CategoriesAppareils";
    private CategorieAppareil getCategorieByLibelle(String libelle) {
        CategorieAppareil categorie = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM CategoriesAppareils WHERE Libelle = ?");
        ) {
            statement.setString(1, libelle);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("ID_Categorie");
                String categorieLibelle = resultSet.getString("Libelle");
                double tarif = resultSet.getDouble("Tarif");
                categorie = new CategorieAppareil(id, categorieLibelle, tarif);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorie;
    }

    public int getCategorieIdByLibelle(String libelle) {
        int categoryId = -1; // default value if category not found
        String sql = "SELECT ID_Categorie FROM CategoriesAppareils WHERE Libelle = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, libelle);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                categoryId = resultSet.getInt("ID_Categorie");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoryId;
    }

    public List<CategorieAppareil> getAllCategories() {
        List<CategorieAppareil> categories = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(SELECT_ALL_CATEGORIES);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("ID_Categorie");
                String libelle = resultSet.getString("Libelle");
                double tarif = resultSet.getDouble("Tarif");
                categories.add(new CategorieAppareil(id, libelle, tarif));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public void insertCategorie(CategorieAppareil categorie) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_CATEGORIE)) {
            pstmt.setString(1, categorie.getLibelle());
            pstmt.setDouble(2, categorie.getTarif());

            pstmt.executeUpdate();
        }
    }
}
