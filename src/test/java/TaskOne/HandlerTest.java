package TaskOne;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandlerTest {

    private Handler handler = new Handler();

    @Test
    public void testRandomNumbers() {

        Long[] unmodifiedArray = {-4L, 7L, 3L, 0L, 2L, -6L, 1L, 1L};
        Long[] modifiedArray = handler.sortedPositiveArrayReceiver(unmodifiedArray);
        Long[] expectedArray = {7L, 3L, 2L, 1L, 1L, 0L};
        assertArrayEquals(expectedArray, modifiedArray);
    }

    @Test
    public void testNegativeArray() {

        Long[] unmodifiedArray = {-4L, -2L, -1L, -3L};
        Long[] modifiedArray = handler.sortedPositiveArrayReceiver(unmodifiedArray);
        Long[] expectedArray = {};
        assertArrayEquals(expectedArray, modifiedArray);
    }

    @Test
    public void testNullException() {

        NullPointerException exception =
                assertThrows(NullPointerException.class, () -> {
                    handler.sortedPositiveArrayReceiver(null);
                }, "Allowed Null");

        assertEquals(
                "Array can't be null.",
                exception.getMessage()
        );
    }

}
