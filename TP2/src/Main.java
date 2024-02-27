public class Main {
    public static void main(String[] args) {
        DossierPharmacie pharmacie = new DossierPharmacie("Pharmacie la joie");

        pharmacie.nouveauPatient("Mohamed Amine", new String[]{"Inflamile", "Antibiotique"});
        pharmacie.nouveauPatient("Ahmed", new String[]{"GripeX"});
        pharmacie.nouveauPatient("Dali", new String[]{"Paracetamol", "GripeX", "Aspirine"});

        pharmacie.affiche();

        pharmacie.ajoutMedicament("Ahmed", "Dolipran1000");

        pharmacie.affichePatient("Ahmed");

        System.out.println("Patients prenant de l'Inflamile : " + pharmacie.affichePatientAvecMedicament("Inflamile"));
    }
}
