import java.util.HashMap;
import java.util.Map;

public class Annuaire {
    private Map<String, Fiche> annuaire;

    public Annuaire() {
        annuaire = new HashMap<>();
    }

    //Modification pour que les fiches apparaissent dans l’ordr des noms (Exercice 2)

    /*public Annuaire() {
        annuaire = new TreeMap<>();
    }
     */

    public void ajouterFiche(String nom, Fiche fiche) {
        annuaire.put(nom, fiche);
    }

    public Fiche chercherFiche(String nom) {
        return annuaire.get(nom);
    }

    public void afficherFiches() {
        for (Fiche fiche : annuaire.values()) {
            System.out.println(fiche);
        }
    }
}
