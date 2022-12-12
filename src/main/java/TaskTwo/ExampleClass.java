package TaskTwo;

import java.time.Instant;

public class ExampleClass {

    private String stringProperty;

    @Property(name="numberProperty")
    private int myNumber;

    @Property(format = "dd.MM.yyyy HH:mm")
    private Instant timeProperty;

    @Override
    public String toString() {
        return "ExampleClass{" +
                "stringProperty='" + stringProperty + '\'' +
                ", myNumber=" + myNumber +
                ", timeProperty=" + timeProperty +
                '}';
    }
}
