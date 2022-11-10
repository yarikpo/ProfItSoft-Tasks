package TaskThree;

import java.util.Collections;
import java.util.List;

public class Handler {

    public List<Figure> receiveSortedFigures(List<Figure> figures) {

        if (figures == null) {
            throw new NullPointerException("List can not be null.");
        }

        Collections.sort(figures);
        return figures;
    }

}
