package model;

public class PieceDetachee {
    private int id;
    private String nom;
    private double prixHT;

    public PieceDetachee(int id, String nom, double prixHT) {
        this.id = id;
        this.nom = nom;
        this.prixHT = prixHT;
    }

    public PieceDetachee(int idPiece) {
        this.id=idPiece;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrixHT() {
        return prixHT;
    }

    public void setPrixHT(double prixHT) {
        this.prixHT = prixHT;
    }
}
