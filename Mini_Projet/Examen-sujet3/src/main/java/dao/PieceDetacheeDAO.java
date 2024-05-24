package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.PieceDetachee;
import util.DatabaseConnection;

public class PieceDetacheeDAO {
    private static final String SELECT_ALL_PIECES = "SELECT * FROM PiecesDetachees";

    public List<PieceDetachee> getAllPiecesDetachees() {
        List<PieceDetachee> pieces = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(SELECT_ALL_PIECES);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("ID_Piece");
                String nom = resultSet.getString("Nom");
                double prixHT = resultSet.getDouble("PrixHT");
                pieces.add(new PieceDetachee(id, nom, prixHT));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pieces;
    }
}
