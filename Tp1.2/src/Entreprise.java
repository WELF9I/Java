import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
class Entreprise {
    private ArrayList<Employe> listeEmployes;

    public Entreprise() {
        listeEmployes = new ArrayList<>();
    }

    public void affiche() {
        for (Employe employe : listeEmployes) {
            System.out.println(employe);
        }
    }

    public void triSalaire() {
        Collections.sort(listeEmployes);
    }

    public void triAge() {
        Collections.sort(listeEmployes, Comparator.comparingInt(Employe::getAge));
    }

    public void triNom() {
        Collections.sort(listeEmployes, Comparator.comparing(Employe::toString));
    }

    public Employe mieuxRemunere() {
        return Collections.max(listeEmployes, Comparator.comparingDouble(Employe::getSalaire));
    }

    public Employe plusAge() {
        return Collections.max(listeEmployes, Comparator.comparingInt(Employe::getAge));
    }

    public void ajouterEmploye(Employe employe) {
        listeEmployes.add(employe);
    }
}