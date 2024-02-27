class Employe implements Comparable<Employe> {
    private int matricule;
    private String nom;
    private String prenom;
    private int age;
    private double salaire;

    public Employe(int matricule, String nom, String prenom, int age, double salaire) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.salaire = salaire;
    }

    public double getSalaire() {
        return salaire;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Employe [matricule=" + matricule + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age
                + ", salaire=" + salaire + "]";
    }

    @Override
    public int compareTo(Employe autreEmploye) {
        return Double.compare(this.salaire, autreEmploye.getSalaire());
    }
}