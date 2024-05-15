package model;

public class PieceAChanger {
    private int id;
    private OrdreReparation ordre;
    private PieceDetachee piece;
    private int quantite;

    public PieceAChanger(int id, OrdreReparation ordre, PieceDetachee piece, int quantite) {
        this.id = id;
        this.ordre = ordre;
        this.piece = piece;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrdreReparation getOrdre() {
        return ordre;
    }

    public void setOrdre(OrdreReparation ordre) {
        this.ordre = ordre;
    }

    public PieceDetachee getPiece() {
        return piece;
    }

    public void setPiece(PieceDetachee piece) {
        this.piece = piece;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
