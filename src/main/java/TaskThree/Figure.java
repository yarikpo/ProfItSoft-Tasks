package TaskThree;

import java.util.Arrays;

public abstract class Figure implements Comparable {

    public Figure() {}

    public abstract Double getVolume();

    @Override
    public int compareTo(Object o) {

        return this.getVolume() - ((Figure)o).getVolume() < 0 ? -1 : 1;
    }

    @Override
    public String toString() {
        return "Figure{" +
                "volume: " + getVolume() +
                '}';
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Figure figure = (Figure) o;
//        return Arrays.equals(params, figure.params);
//    }
//
//    @Override
//    public int hashCode() {
//        return Arrays.hashCode(params);
//    }
}
