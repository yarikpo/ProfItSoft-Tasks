package TaskThree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BallTest {

    @Test
    public void testZeroArguments() {

        IllegalStateException exception =
                Assertions.assertThrows(IllegalStateException.class, () -> {
                    new Ball().getVolume();
                }, "Incorrect number of arguments accepted");
        Assertions.assertEquals("Number of parameters in ball has to be equalled one.", exception.getMessage());
    }

    @Test
    public void testOneArgument() {

        Assertions.assertEquals(4.0/3.0 * Math.PI, new Ball(1.0).getVolume());
    }

    @Test
    public void testTwoArguments() {

        IllegalStateException exception =
                Assertions.assertThrows(IllegalStateException.class, () -> {
                    new Ball(1.0, 2.0).getVolume();
                }, "Incorrect number of arguments accepted");
        Assertions.assertEquals("Number of parameters in ball has to be equalled one.", exception.getMessage());
    }

}
