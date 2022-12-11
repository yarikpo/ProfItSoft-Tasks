package TaskOne;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class HandlerTest {

    @Test
    public void testSingleHandler() {

        int start = (int) System.currentTimeMillis();
        File directory = new File("src/test/resources/TaskOne/XML_FILES");
        try {
            Handler.makeStatistics(directory, 1);
        }
        catch (InterruptedException | ExecutionException | IOException e) {
            e.printStackTrace();
            Assertions.fail();
        }
        int end = (int) System.currentTimeMillis();

        System.out.println("Program has been working for - " + (end - start) + " mills.");
    }

    @Test
    public void testTwoHandler() {

        int start = (int) System.currentTimeMillis();
        File directory = new File("src/test/resources/TaskOne/XML_FILES");
        try {
            Handler.makeStatistics(directory, 2);
        }
        catch (InterruptedException | ExecutionException | IOException e) {
            e.printStackTrace();
            Assertions.fail();
        }
        int end = (int) System.currentTimeMillis();

        System.out.println("Program has been working for - " + (end - start) + " mills.");
    }

    @Test
    public void testFourHandler() {

        int start = (int) System.currentTimeMillis();
        File directory = new File("src/test/resources/TaskOne/XML_FILES");
        try {
            Handler.makeStatistics(directory, 4);
        }
        catch (InterruptedException | ExecutionException | IOException e) {
            e.printStackTrace();
            Assertions.fail();
        }
        int end = (int) System.currentTimeMillis();

        System.out.println("Program has been working for - " + (end - start) + " mills.");
    }

    @Test
    public void testEightHandler() {

        int start = (int) System.currentTimeMillis();
        File directory = new File("src/test/resources/TaskOne/XML_FILES");
        try {
            Handler.makeStatistics(directory, 8);
        }
        catch (InterruptedException | ExecutionException | IOException e) {
            e.printStackTrace();
            Assertions.fail();
        }
        int end = (int) System.currentTimeMillis();

        System.out.println("Program has been working for - " + (end - start) + " mills.");
    }

}
