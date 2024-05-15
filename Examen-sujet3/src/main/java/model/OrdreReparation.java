package model;

import java.util.List;

public class OrdreReparation {
    private int id;
    private Appareil appareil;
    private int nbHeuresMO;
    private List<PieceAChanger> piecesAChanger;

    public OrdreReparation(int id, Appareil appareil, int nbHeuresMO, List<PieceAChanger> piecesAChanger) {
        this.id = id;
        this.appareil = appareil;
        this.nbHeuresMO = nbHeuresMO;
        this.piecesAChanger = piecesAChanger;
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
}
