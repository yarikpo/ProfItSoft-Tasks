package TaskThree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CylinderTest {

    @Test
    public void testTwoArguments() {

        Assertions.assertEquals(Math.PI, new Cylinder(1.0, 1.0).getVolume());
    }

}
