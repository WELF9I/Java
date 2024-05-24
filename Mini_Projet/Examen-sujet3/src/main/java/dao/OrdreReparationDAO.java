package dao;

import model.*;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdreReparationDAO {
    private static final String SELECT_ALL_ORDRES = "SELECT * FROM OrdresReparation";
    private static final String SELECT_APPAREIL_BY_ID = "SELECT * FROM Appareil WHERE id = ?";
    private static final String SELECT_CLIENT_BY_ID = "SELECT * FROM Client WHERE id_client = ?";
    private static final String SELECT_CATEGORIE_BY_ID = "SELECT * FROM CategoriesAppareil WHERE id = ?";
    private static final String INSERT_ORDRE_REPARATION = "INSERT INTO OrdresReparation (ID_Appareil, NbHeuresMO) VALUES (?, ?)";

    private Appareil getAppareilById(int idAppareil) {
        Appareil appareil = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(SELECT_APPAREIL_BY_ID)) {
            statement.setInt(1, idAppareil);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("ID_Appareil");
                    int clientId = resultSet.getInt("ID_Client");
                    int categorieId = resultSet.getInt("ID_Categorie");
                    String description = resultSet.getString("Description");
                    String marque = resultSet.getString("Marque");

                    Client client = getClientById(clientId);
                    CategorieAppareil categorie = getCategorieAppareilById(categorieId);

                    appareil = new Appareil(id, client, categorie, description, marque);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appareil;
    }

    private Client getClientById(int clientId) {
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

    private CategorieAppareil getCategorieAppareilById(int categorieId) {
        CategorieAppareil categorie = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(SELECT_CATEGORIE_BY_ID)) {
            statement.setInt(1, categorieId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("ID_Categorie");
                    String libelle = resultSet.getString("Libelle");
                    double tarif = resultSet.getDouble("Tarif");

                    categorie = new CategorieAppareil(id, libelle, tarif);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorie;
    }

    public void insertOrdreReparation(OrdreReparation ordreReparation) throws SQLException {
        String sql = "INSERT INTO OrdresReparation (ID_Appareil, NbHeuresMO, Date, ID_Piece) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, ordreReparation.getAppareil().getId());
            pstmt.setInt(2, ordreReparation.getNbHeuresMO());
            pstmt.setString(3, ordreReparation.getDate());
            pstmt.setNull(4, java.sql.Types.INTEGER); // Assuming ID_Piece is optional and set later

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    ordreReparation.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating ordre reparation failed, no ID obtained.");
                }
            }
        }
    }

    public List<OrdreReparation> getAllOrdresReparation() {
        List<OrdreReparation> ordresReparation = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_ORDRES);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int idOrdre = rs.getInt("ID_Ordre");
                int idAppareil = rs.getInt("ID_Appareil");
                int nbHeuresMO = rs.getInt("NbHeuresMO");
                String date = rs.getString("Date");

                Appareil appareil = getAppareilById(idAppareil);
                List<PieceAChanger> piecesAChanger = getPieceAChangerByOrdreId(idOrdre);

                OrdreReparation ordreReparation = new OrdreReparation(idOrdre, appareil, nbHeuresMO, piecesAChanger);
                ordresReparation.add(ordreReparation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ordresReparation;
    }

    private List<PieceAChanger> getPieceAChangerByOrdreId(int idOrdre) {
        List<PieceAChanger> piecesAChanger = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM PiecesAChanger WHERE ID_Ordre = ?");
        ) {
            pstmt.setInt(1, idOrdre);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int idPieceAChanger = rs.getInt("ID_PieceAChanger");
                    int idPiece = rs.getInt("ID_Piece");
                    int quantite = rs.getInt("Quantite");

                    PieceDetachee pieceDetachee = getPieceDetacheeById(idPiece);
                    piecesAChanger.add(new PieceAChanger(idPieceAChanger, new OrdreReparation(idOrdre), pieceDetachee, quantite));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return piecesAChanger;
    }

    private PieceDetachee getPieceDetacheeById(int idPiece) {
        PieceDetachee pieceDetachee = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM PiecesDetachees WHERE ID_Piece = ?");
        ) {
            pstmt.setInt(1, idPiece);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("ID_Piece");
                    String nom = rs.getString("Nom");
                    double prixHT = rs.getDouble("PrixHT");

                    pieceDetachee = new PieceDetachee(id, nom, prixHT);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pieceDetachee;
    }




}
