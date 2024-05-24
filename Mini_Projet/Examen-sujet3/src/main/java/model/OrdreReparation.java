package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdreReparation {
    private int id;
    private Appareil appareil;
    private int nbHeuresMO;
    private String date;
    private List<PieceAChanger> piecesAChanger;

    public OrdreReparation(int id, Appareil appareil, int nbHeuresMO,List<PieceAChanger> piecesAChanger) {
        this.id = id;
        this.appareil = appareil;
        this.nbHeuresMO = nbHeuresMO;
        this.date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        this.piecesAChanger = piecesAChanger;
    }

    public OrdreReparation(int idOrdre) {
        this.id=idOrdre;
    }

    public OrdreReparation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Appareil getAppareil() {
        return appareil;
    }

    public void setAppareil(Appareil appareil) {
        this.appareil = appareil;
    }

    public int getNbHeuresMO() {
        return nbHeuresMO;
    }

    public void setNbHeuresMO(int nbHeuresMO) {
        this.nbHeuresMO = nbHeuresMO;
    }

    public List<PieceAChanger> getPiecesAChanger() {
        return piecesAChanger;
    }

    public void setPiecesAChanger(List<PieceAChanger> piecesAChanger) {
        this.piecesAChanger = piecesAChanger;
    }

    public String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
}
