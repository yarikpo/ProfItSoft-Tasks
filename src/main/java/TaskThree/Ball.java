package TaskThree;

import java.util.Objects;

public class Ball extends Figure {

    private double radius;

    public Ball(double radius) {
        this.radius = radius;
    }
    @Override
    public Double getVolume() {
        // TODO check for overflowing
        Double volume = 4.0/3.0 * Math.PI * radius * radius * radius;

        return volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ball ball = (Ball) o;
        return Double.compare(ball.radius, radius) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius);
    }

    @Override
    public String toString() {
        return "Ball{" +
                "radius=" + radius +
                ", volume=" + getVolume() +
                '}';
    }
}
