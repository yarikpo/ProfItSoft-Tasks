package TaskThree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CylinderTest {

    @Test
    public void testZeroArguments() {

        IllegalStateException exception =
                Assertions.assertThrows(IllegalStateException.class, () -> {
                    new Cylinder().getVolume();
                }, "Incorrect number of arguments accepted");
        Assertions.assertEquals("Number of parameters in cylinder has to be equalled two.", exception.getMessage());
    }

    @Test
    public void testOneArgument() {

        IllegalStateException exception =
                Assertions.assertThrows(IllegalStateException.class, () -> {
                    new Cylinder(1.0).getVolume();
                }, "Incorrect number of arguments accepted");
        Assertions.assertEquals("Number of parameters in cylinder has to be equalled two.", exception.getMessage());
    }

    @Test
    public void testTwoArguments() {

        Assertions.assertEquals(Math.PI, new Cylinder(1.0, 1.0).getVolume());
    }

}
