import java.util.Comparator;

public class CompareMoyenne implements Comparator<Etudiant> {
    @Override
    public int compare(Etudiant etudiant1, Etudiant etudiant2) {
        return Float.compare(etudiant1.getMoyenne(), etudiant2.getMoyenne());
    }
}
