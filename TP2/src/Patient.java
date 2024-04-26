import java.util.*;

public class Patient {
    private String nom;
    private Set<String> ordonnance;

    public Patient(String n) {
        nom = n;
        ordonnance = new HashSet<>();
    }

    public String getNom() {
        return nom;
    }

    public boolean ordonnanceVide() {
        return ordonnance.isEmpty();
    }

    public void ajoutMedicament(String m) {
        ordonnance.add(m);
    }

    public void affiche() {
        System.out.println("Nom du patient : " + nom);
        System.out.println("Ordonnance : " + ordonnance);
    }

    public boolean contientMedicament(String m) {
        return ordonnance.contains(m);
    }

    public void trieOrdonnace() {
        List<String> sortedOrdonnance = new ArrayList<String>(ordonnance);
        Collections.sort(sortedOrdonnance);
        ordonnance = new LinkedHashSet<>(sortedOrdonnance);
        
    }
}