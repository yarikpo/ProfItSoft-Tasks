package TaskTwo;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class HandlerTest {

    private Handler handler = new Handler();

    @Test
    public void testSingleLine() {

        List<String> unmodifiedLines = List.of("#hello   #hi   #hello, friend");
        Map<String, Integer> modifiedMap = handler.receiveTopFiveHashTags(unmodifiedLines);
        Map<String, Integer> expectedMap = Map.ofEntries(
                Map.entry("hello", 1),
                Map.entry("hi", 1)
        );
        assertTrue(expectedMap.equals(modifiedMap));
    }

    @Test
    public void testMultipleStrings() {

        List<String> unmodifiedLines = List.of("#hello   #hi   #hello, friend", "   #hello   fire #see   #bat", "#f1#bat", "#f2", "f3#f2", "####");
        Map<String, Integer> modifiedMap = handler.receiveTopFiveHashTags(unmodifiedLines);
        Map<String, Integer> expectedMap = Map.ofEntries(
                Map.entry("hello", 2),
                Map.entry("bat", 2),
                Map.entry("f2", 2),
                Map.entry("f1", 1),
                Map.entry("hi", 1)
        );
        assertTrue(expectedMap.equals(modifiedMap));
    }

    @Test
    public void testNullable() {

        NullPointerException exception =
                assertThrows(NullPointerException.class, () -> {
                   handler.receiveTopFiveHashTags(null);
                }, "Null is allowed.");

        assertEquals("List can not be null.", exception.getMessage());
    }

}
