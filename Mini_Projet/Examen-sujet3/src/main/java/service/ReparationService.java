package service;

import dao.*;
import model.*;

import java.sql.SQLException;
import java.util.List;

public class ReparationService {
    private AppareilDAO appareilDAO;
    private CategorieAppareilDAO categorieAppareilDAO;
    private OrdreReparationDAO ordreReparationDAO;
    private PieceAChangerDAO pieceAChangerDAO;
    private PieceDetacheeDAO pieceDetacheeDAO;

    public ReparationService() {
        this.appareilDAO = new AppareilDAO();
        this.categorieAppareilDAO = new CategorieAppareilDAO();
        this.ordreReparationDAO = new OrdreReparationDAO();
        this.pieceAChangerDAO = new PieceAChangerDAO();
        this.pieceDetacheeDAO = new PieceDetacheeDAO();
    }

    public List<Appareil> getAllAppareils() {
        return appareilDAO.getAllAppareils();
    }

    public List<CategorieAppareil> getAllCategories() {
        return categorieAppareilDAO.getAllCategories();
    }

    public List<OrdreReparation> getAllOrdresReparation() {
        return ordreReparationDAO.getAllOrdresReparation();
    }

    public List<PieceDetachee> getAllPiecesDetachees() {
        return pieceDetacheeDAO.getAllPiecesDetachees();
    }

    public List<PieceAChanger> getPiecesAChangerByOrdre(int ordreId) {
        return pieceAChangerDAO.getPiecesAChangerByOrdre(ordreId);
    }

    public void insertAppareil(Appareil appareil) {
        appareilDAO.insertAppareil(appareil);
    }

    public void insertOrdreReparation(OrdreReparation ordreReparation) throws SQLException {
        ordreReparationDAO.insertOrdreReparation(ordreReparation);
    }

    public void insertPieceAChanger(PieceAChanger pieceAChanger) {
        pieceAChangerDAO.insertPieceAChanger(pieceAChanger);
    }
    public int getCategorieIdByLibelle(String libelle) {
        return categorieAppareilDAO.getCategorieIdByLibelle(libelle);
    }
}
