package model;

public class Appareil {
    private int id;
    private Client client;
    private CategorieAppareil categorie;
    private String description;
    private String marque;

    public Appareil(int id, Client client, CategorieAppareil categorie, String description, String marque) {
        this.id = id;
        this.client = client;
        this.categorie = categorie;
        this.description = description;
        this.marque = marque;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public CategorieAppareil getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieAppareil categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }
}
