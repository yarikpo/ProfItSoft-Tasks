package TaskThree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CubeTest {

    @Test
    public void testOneArgument() {

        Assertions.assertEquals(1.0, new Cube(1.0).getVolume());
    }

}
