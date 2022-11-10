package TaskThree;

public class Cube extends Figure {

    public Cube(Double ... params) {
        super(params);
    }
    @Override
    public Double getVolume() {

        if (getParams().length != 1) {
            throw new IllegalStateException("Number of parameters in cube has to be equalled one.");
        }
        // TODO check for overflowing
        Double a = getParams()[0];
        Double volume = a * a * a;

        return volume;
    }

//    @Override
//    public String toString() {
//        return "Cube{volume: " + getVolume() + "}";
//    }
}
