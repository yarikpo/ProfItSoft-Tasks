package TaskThree;

import java.util.Objects;

public class Cylinder extends Figure {

    private double radius, height;

    public Cylinder(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }
    @Override
    public Double getVolume() {
        // TODO check for overflowing

        Double volume = Math.PI * radius * radius * height;
        return volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cylinder cylinder = (Cylinder) o;
        return Double.compare(cylinder.radius, radius) == 0 && Double.compare(cylinder.height, height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius, height);
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "radius=" + radius +
                ", height=" + height +
                ", volume=" + getVolume() +
                '}';
    }
}
