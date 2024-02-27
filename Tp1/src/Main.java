import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Etudiant etudiant1 = new Etudiant("123", "Mohamed Amine");
        etudiant1.addNote(new Note("DCP", 15));
        etudiant1.addNote(new Note("JAVA", 18));
        etudiant1.addNote(new Note("SGBD", 12));

        Etudiant etudiant2 = new Etudiant("456", "Welfeki");
        etudiant2.addNote(new Note("DCP", 20));
        etudiant2.addNote(new Note("JAVA", 17));
        etudiant2.addNote(new Note("SGBD", 14));

        List<Etudiant> etudiants = new ArrayList<>();
        etudiants.add(etudiant2);
        etudiants.add(etudiant1);

        // tri par matricule
        Collections.sort(etudiants);
        System.out.println("Tri par matricule:");
        for (Etudiant etudiant : etudiants) {
            System.out.println(etudiant.getNom() + " - Matricule: " + etudiant.getMatricule());
        }

        // tri par moyenne
        Collections.sort(etudiants, new CompareMoyenne());
        System.out.println("\nTri par moyenne:");
        for (Etudiant etudiant : etudiants) {
            System.out.println(etudiant.getNom() + " - Moyenne: " + etudiant.getMoyenne());
        }

        // tri par nom
        Collections.sort(etudiants, new CompareNom());
        System.out.println("\nTri par nom:");
        for (Etudiant etudiant : etudiants) {
            System.out.println(etudiant.getNom());
        }

        //Exercice 2 : Table associative : un annuaire

        /*
        Annuaire annuaire = new Annuaire();
        Scanner scanner = new Scanner(System.in);
        String commande;

        do {
            System.out.println("Entrez une commande (+nom, ?nom, !, .): ");
            commande = scanner.nextLine();

            switch (commande.charAt(0)) {
                case '+':
                    System.out.println("Entrez le nom: ");
                    String nom = scanner.nextLine();
                    System.out.println("Entrez le numéro: ");
                    String numero = scanner.nextLine();
                    System.out.println("Entrez l'adresse: ");
                    String adresse = scanner.nextLine();
                    annuaire.ajouterFiche(nom, new Fiche(nom, numero, adresse));
                    break;
                case '?':
                    System.out.println("Entrez le nom à rechercher: ");
                    String nomRecherche = scanner.nextLine();
                    Fiche fiche = annuaire.chercherFiche(nomRecherche);
                    if (fiche != null) {
                        System.out.println("Fiche trouvée : " + fiche);
                    } else {
                        System.out.println("Aucune fiche trouvée pour ce nom.");
                    }
                    break;
                case '!':
                    annuaire.afficherFiches();
                    break;
                case '.':
                    System.out.println("Arrêt du programme.");
                    break;
                default:
                    System.out.println("Commande invalide.");
            }
        } while (!commande.equals("."));
        scanner.close();
*/

    }
}
