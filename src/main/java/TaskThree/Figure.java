package TaskThree;

import java.util.Arrays;

public abstract class Figure implements Comparable {

    private Double[] params;

    public Figure() {}

    public Figure(Double ... params) {

        this.params = params;
    }

    public abstract Double getVolume();

    public Double[] getParams() {

        return this.params;
    }

    public void setParams(Double[] params) {

        this.params = params;
    }

    @Override
    public int compareTo(Object o) {

        return this.getVolume() - ((Figure)o).getVolume() < 0 ? -1 : 1;
    }

    @Override
    public String toString() {
        return "Figure{" +
                "params=" + Arrays.toString(params) +
                ", volume: " + getVolume() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Figure figure = (Figure) o;
        return Arrays.equals(params, figure.params);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(params);
    }
}
