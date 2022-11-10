package TaskThree;

public class Ball extends Figure {

    public Ball(Double ... params) {
        super(params);
    }
    @Override
    public Double getVolume() {

        if (getParams().length != 1) {
            throw new IllegalStateException("Number of parameters in ball has to be equalled one.");
        }
        // TODO check for overflowing
        Double radius = getParams()[0];
        Double volume = 4.0/3.0 * Math.PI * radius * radius * radius;

        return volume;
    }

}
