import java.util.List;

class Stats {
    public static float calculerMoyenne(List<? extends Statisticable> list) {
        float sum = 0;
        for (Statisticable item : list) {
            sum += item.getValue();
        }
        return sum / list.size();
    }

    public static <T extends Statisticable> T trouverMeilleur(List<T> list) {
        if (list.isEmpty()) return null;
        T meilleur = list.get(0);
        for (T item : list) {
            if (item.getValue() > meilleur.getValue()) {
                meilleur = item;
            }
        }
        return meilleur;
    }

    public static <T extends Statisticable> T trouverMoinsBon(List<T> list) {
        if (list.isEmpty()) return null;
        T moinsBon = list.get(0);
        for (T item : list) {
            if (item.getValue() < moinsBon.getValue()) {
                moinsBon = item;
            }
        }
        return moinsBon;
    }
}