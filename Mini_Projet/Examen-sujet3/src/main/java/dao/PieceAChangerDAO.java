package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.*;
import util.DatabaseConnection;

public class PieceAChangerDAO {
    private static final String SELECT_ALL_PIECES_A_CHANGER = "SELECT * FROM PiecesAChanger";
    private static final String SELECT_PIECES_BY_ORDRE = "SELECT * FROM PiecesAChanger WHERE ID_Ordre = ?";
    private static final String INSERT_PIECE_A_CHANGER = "INSERT INTO PiecesAChanger (ID_Ordre, ID_Piece, Quantite) VALUES (?, ?, ?)";

    public List<PieceAChanger> getAllPiecesAChanger() {
        List<PieceAChanger> piecesAChanger = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(SELECT_ALL_PIECES_A_CHANGER);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("ID_PieceAChanger");
                int idOrdre = resultSet.getInt("ID_Ordre");
                int idPiece = resultSet.getInt("ID_Piece");
                int quantite = resultSet.getInt("Quantite");
                OrdreReparation ordreReparation = getOrdreReparationById(idOrdre);
                PieceDetachee pieceDetachee = getPieceDetacheeById(idPiece);
                piecesAChanger.add(new PieceAChanger(id, ordreReparation, pieceDetachee, quantite));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return piecesAChanger;
    }

    private OrdreReparation getOrdreReparationById(int idOrdre) {
        OrdreReparation ordreReparation = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM OrdresReparation WHERE ID_Ordre = ?")) {
            statement.setInt(1, idOrdre);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int idAppareil = resultSet.getInt("ID_Appareil");
                int nbHeuresMO = resultSet.getInt("NbHeuresMO");
                Appareil appareil = getAppareilById(idAppareil);
                List<PieceAChanger> piecesAChanger = getPieceAChangerByOrdreId(idOrdre);
                ordreReparation = new OrdreReparation(idOrdre, appareil, nbHeuresMO, piecesAChanger);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordreReparation;
    }

    public List<PieceAChanger> getPieceAChangerByOrdreId(int idOrdre) {
        List<PieceAChanger> piecesAChanger = new ArrayList<>();
        String sql = "SELECT * FROM PieceAChanger WHERE ID_Ordre = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idOrdre);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int idPieceAChanger = rs.getInt("ID_PieceAChanger");
                int idPiece = rs.getInt("ID_Piece");
                int quantite = rs.getInt("Quantite");
                PieceDetachee pieceDetachee = getPieceDetacheeById(idPiece);
                OrdreReparation ordreReparation = getOrdreReparationById(idOrdre);
                PieceAChanger pieceAChanger = new PieceAChanger(idPieceAChanger, ordreReparation, pieceDetachee, quantite);
                piecesAChanger.add(pieceAChanger);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return piecesAChanger;
    }

    private PieceDetachee getPieceDetacheeById(int idPiece) {
        PieceDetachee pieceDetachee = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM PiecesDetachees WHERE ID_Piece = ?")) {
            statement.setInt(1, idPiece);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nom = resultSet.getString("Nom");
                double prixHT = resultSet.getDouble("PrixHT");
                pieceDetachee = new PieceDetachee(idPiece, nom, prixHT);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pieceDetachee;
    }

    private Appareil getAppareilById(int idAppareil) {
        Appareil appareil = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM Appareils WHERE ID_Appareil = ?")) {
            statement.setInt(1, idAppareil);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int clientId = resultSet.getInt("ID_Client");
                int categorieId = resultSet.getInt("ID_Categorie");
                String description = resultSet.getString("Description");
                String marque = resultSet.getString("Marque");
                Client client = getClientById(clientId);
                CategorieAppareil categorie = getCategorieById(categorieId);
                appareil = new Appareil(idAppareil, client, categorie, description, marque);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appareil;
    }

    private Client getClientById(int clientId) {
        Client client = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM Clients WHERE ID_Client = ?")) {
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
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM CategoriesAppareils WHERE ID_Categorie = ?")) {
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

    public List<PieceAChanger> getPiecesAChangerByOrdre(int ordreId) {
        List<PieceAChanger> pieces = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(SELECT_PIECES_BY_ORDRE)) {
            statement.setInt(1, ordreId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int idPieceAChanger = resultSet.getInt("ID_PieceAChanger");
                    int idOrdre = resultSet.getInt("ID_Ordre");
                    int idPiece = resultSet.getInt("ID_Piece");
                    int quantite = resultSet.getInt("Quantite");
                    OrdreReparation ordreReparation = getOrdreReparationById(idOrdre);
                    PieceDetachee pieceDetachee = getPieceDetacheeById(idPiece);
                    pieces.add(new PieceAChanger(idPieceAChanger, ordreReparation, pieceDetachee, quantite));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pieces;
    }

    public void insertPieceAChanger(PieceAChanger pieceAChanger) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(INSERT_PIECE_A_CHANGER)) {
            statement.setInt(1, pieceAChanger.getOrdre().getId());
            statement.setInt(2, pieceAChanger.getPiece().getId());
            statement.setInt(3, pieceAChanger.getQuantite());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
