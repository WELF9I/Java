import java.util.*;
public class DossierPharmacie {
    private String nom;
    private HashMap<String, Patient> patients;

    public DossierPharmacie(String n) {
        nom = n;
        patients = new HashMap<>();
    }

    public void nouveauPatient(String nom, String[] ord) {
        Patient patient = new Patient(nom.toLowerCase());
        for (String medicament : ord) {
            patient.ajoutMedicament(medicament);
        }
        patients.put(patient.getNom(), patient);
    }

    public boolean ajoutMedicament(String nom, String m) {
        Patient patient = patients.get(nom.toLowerCase());
        if (patient != null) {
            patient.ajoutMedicament(m);
            return true;
        }
        return false;
    }
    
    public void affichePatient(String nom) {
        Patient patient = patients.get(nom.toLowerCase());
        if (patient != null) {
            patient.affiche();
        } else {
            System.out.println("Patient introuvable.");
        }
    }

    public void affiche() {
        System.out.println("Nom de la pharmacie : " + nom);
        for (Patient patient : patients.values()) {
            patient.affiche();
        }
    }

    public Collection<String> affichePatientAvecMedicament(String m) {
        List<String> patientsAvecMedicament = new ArrayList<>();
        for (Patient patient : patients.values()) {
            if (patient.contientMedicament(m)) {
                patientsAvecMedicament.add(patient.getNom());
            }
        }
        return patientsAvecMedicament;
    }
}