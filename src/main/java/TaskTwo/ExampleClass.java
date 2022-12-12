package TaskTwo;

import java.time.Instant;

public class ExampleClass {

    private String stringProperty;

    @Property(name="numberProperty")
    private int myNumber;

    @Property(format = "dd.MM.yyyy tt:mm")
    private Instant timeProperty;
}
