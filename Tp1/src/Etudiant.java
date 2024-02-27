import java.util.ArrayList;
import java.util.List;

class Etudiant implements Statisticable, Comparable<Etudiant> {
    private String matricule;
    private String nom;
    private List<Note> notes;

    public Etudiant(String matricule, String nom) {
        this.matricule = matricule;
        this.nom = nom;
        this.notes = new ArrayList<>();
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    public float getMoyenne() {
        if (notes.isEmpty()) return 0;
        float sum = 0;
        for (Note note : notes) {
            sum += note.getValeur();
        }
        return sum / notes.size();
    }

    public Note getMeilleureNote() {
        if (notes.isEmpty()) return null;
        Note meilleureNote = notes.get(0);
        for (Note note : notes) {
            if (note.getValeur() > meilleureNote.getValeur()) {
                meilleureNote = note;
            }
        }
        return meilleureNote;
    }

    public Note getMoinsBonneNote() {
        if (notes.isEmpty()) return null;
        Note moinsBonneNote = notes.get(0);
        for (Note note : notes) {
            if (note.getValeur() < moinsBonneNote.getValeur()) {
                moinsBonneNote = note;
            }
        }
        return moinsBonneNote;
    }

    @Override
    public float getValue() {
        return getMoyenne();
    }

    @Override
    public int compareTo(Etudiant autreEtudiant) {
        return this.matricule.compareTo(autreEtudiant.matricule);
    }

    public String getNom() {
        return nom;
    }
    public String getMatricule() {
        return matricule;
    }
}
