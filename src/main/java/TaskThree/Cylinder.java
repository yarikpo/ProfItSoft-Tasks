package TaskThree;

public class Cylinder extends Figure {

    public Cylinder(Double ... params) {
        super(params);
    }
    @Override
    public Double getVolume() {

        if (getParams().length != 2) {
            throw new IllegalStateException("Number of parameters in cylinder has to be equalled two.");
        }
        // TODO check for overflowing
        Double radius = getParams()[0];
        Double height = getParams()[1];

        Double volume = Math.PI * radius * radius * height;
        return volume;
    }

}
