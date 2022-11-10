package TaskThree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CubeTest {

    @Test
    public void testZeroArguments() {

        IllegalStateException exception =
                Assertions.assertThrows(IllegalStateException.class, () -> {
                    new Cube().getVolume();
                }, "Incorrect number of arguments accepted");
        Assertions.assertEquals("Number of parameters in cube has to be equalled one.", exception.getMessage());
    }

    @Test
    public void testOneArgument() {

        Assertions.assertEquals(1.0, new Cube(1.0).getVolume());
    }

    @Test
    public void testTwoArguments() {

        IllegalStateException exception =
                Assertions.assertThrows(IllegalStateException.class, () -> {
                    new Cube(1.0, 2.0).getVolume();
                }, "Incorrect number of arguments accepted");
        Assertions.assertEquals("Number of parameters in cube has to be equalled one.", exception.getMessage());
    }

}
