package TaskThree;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HandlerTest {

    private Handler handler = new Handler();

    @Test
    public void testNullable() {

        NullPointerException exception =
                assertThrows(NullPointerException.class, () -> {
                    handler.receiveSortedFigures(null);
                }, "Null is allowed.");
        assertEquals("List can not be null.", exception.getMessage());
    }

    @Test
    public void testCorrectFigures() {

        List<Figure> figures = new ArrayList<>(List.of(
                new Cube(3.0), // 27
                new Cube(2.0), // 8
                new Cube(4.0), // 64
                new Ball(3.0), //113
                new Cylinder(1.0, 3.0) // 9
        ));
        List<Figure> modifiedList = handler.receiveSortedFigures(figures);
        List<Figure> expectedList = new ArrayList<>(List.of(
                new Cube(2.0), // 8
                new Cylinder(1.0, 3.0), // 9
                new Cube(3.0), // 27
                new Cube(4.0), // 64
                new Ball(3.0) //113
        ));
        assertTrue(expectedList.equals(modifiedList));
    }

}
