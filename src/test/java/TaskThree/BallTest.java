package TaskThree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BallTest {

    @Test
    public void testOneArgument() {

        Assertions.assertEquals(4.0/3.0 * Math.PI, new Ball(1.0).getVolume());
    }

}
