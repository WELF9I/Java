import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
class Departement {
    private ArrayList<Employe> listeEmployes;
    public Departement() {
        listeEmployes = new ArrayList<>();
    }

    public void affiche() {
        for (Employe employe : listeEmployes) {
            System.out.println(employe);
        }
    }

    public void triNom() {
        Collections.sort(listeEmployes, Comparator.comparing(Employe::toString));
    }

    public void ajouterEmploye(Employe employe) {
        listeEmployes.add(employe);
    }
}
