package TaskThree;

import java.util.Objects;

public class Cube extends Figure {

    private double edge;

    public Cube(double edge) {
        this.edge = edge;
    }
    @Override
    public Double getVolume() {
        // TODO check for overflowing
        Double volume = edge * edge * edge;

        return volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cube cube = (Cube) o;
        return Double.compare(cube.edge, edge) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(edge);
    }

    @Override
    public String toString() {
        return "Cube{" +
                "edge=" + edge +
                ", volume=" + getVolume() +
                '}';
    }
}
