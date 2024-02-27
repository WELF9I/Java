public class TestEntreprise {
    public static void main(String[] args) {

        Entreprise entreprise = new Entreprise();
        entreprise.ajouterEmploye(new Employe(1, "khmakhem", "Dali", 40, 4000));
        entreprise.ajouterEmploye(new Employe(2, "Mohsen", "Ahmed", 30, 3000));
        entreprise.ajouterEmploye(new Employe(3, "Welfeki", "Mohamed Amine", 20, 3500));

        System.out.println("Liste des employés avant le tri :");
        entreprise.affiche();
        System.out.println();

        System.out.println("----------Tri par salaire----------");
        entreprise.triSalaire();
        entreprise.affiche();
        System.out.println();

        System.out.println("----------Tri par âge----------");
        entreprise.triAge();
        entreprise.affiche();
        System.out.println();

        System.out.println("Employé le mieux rémunéré :");
        System.out.println(entreprise.mieuxRemunere());
        System.out.println();

        System.out.println("Employé le plus âgé :");
        System.out.println(entreprise.plusAge());
        System.out.println();

        Departement departement = new Departement();
        departement.ajouterEmploye(new Employe(4, "Omri", "Ahmed", 45, 3800));
        departement.ajouterEmploye(new Employe(5, "Nahdi", "Lamin", 28, 3200));

        System.out.println("Liste des employés du département :");
        departement.affiche();
        System.out.println();

        System.out.println("----------Tri par nom dans le département----------");
        departement.triNom();
        departement.affiche();
    }
}