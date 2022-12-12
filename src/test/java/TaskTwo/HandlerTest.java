package TaskTwo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class HandlerTest {

    @Test
    public void testHandler() {

        try {
            Class<ExampleClass> cls = ExampleClass.class;
            Path propertyPath = Paths.get("src/test/resources/TaskTwo/properties.txt");
            ExampleClass ex = Handler.loadFromProperties(cls, propertyPath);
            System.out.println(ex);
            Assertions.assertNotNull("Troubles with HashMap properties.");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    public void testSameAnnotations() {

        Class<ExampleClass2> cls = ExampleClass2.class;
        Path propertyPath = Paths.get("src/test/resources/TaskTwo/properties2.txt");

        IllegalStateException exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            Handler.loadFromProperties(cls, propertyPath);
        }, "There must be exactly 1 field for each annotation.");
        Assertions.assertEquals("File content is incorrect.", exception.getMessage());
    }

    @Test
    public void testIllegalArguments() {

        Class<ExampleClass3> cls = ExampleClass3.class;
        Path propertyPath = Paths.get("src/test/resources/TaskTwo/properties3.txt");

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Handler.loadFromProperties(cls, propertyPath);
        }, "NumberFormatException");
        Assertions.assertEquals("Illegal type.", exception.getMessage());
    }

    @Test
    public void testNotCorrespondedAnnotation() {

        Class<ExampleClass3> cls = ExampleClass3.class;
        Path propertyPath = Paths.get("src/test/resources/TaskTwo/properties3-1.txt");

        IllegalStateException exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            Handler.loadFromProperties(cls, propertyPath);
        }, "There must not be keys in property file which does not corresponds class fields.");
        Assertions.assertEquals("File content is incorrect.", exception.getMessage());
    }

    @Test
    public void testWrongInstant() {

        Class<ExampleClass3> cls = ExampleClass3.class;
        Path propertyPath = Paths.get("src/test/resources/TaskTwo/properties3-2.txt");

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Handler.loadFromProperties(cls, propertyPath);
        }, "Incorrect pattern in instant property.");
        Assertions.assertEquals("Incorrect Instant variable format.", exception.getMessage());
    }


    @Test
    public void testWrongPattern() {

        Class<ExampleClass4> cls = ExampleClass4.class;
        Path propertyPath = Paths.get("src/test/resources/TaskTwo/properties3-2.txt");

        IllegalStateException exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            Handler.loadFromProperties(cls, propertyPath);
        }, "Pattern is incorrect. Check out class annotation @Property(format=).");
        Assertions.assertEquals("Incorrect format property in class.", exception.getMessage());
    }


    @Test
    public void testWrongPropertyFile() {

        Class<ExampleClass> cls = ExampleClass.class;
        Path propertyPath = Paths.get("src/test/resources/TaskTwo/properties4.txt");

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Handler.loadFromProperties(cls, propertyPath);
        }, "Incorrect format in property file.");
        Assertions.assertEquals("Troubles with property file content.", exception.getMessage());
    }

    @Test
    public void testNoFile() {

        Class<ExampleClass> cls = ExampleClass.class;
        Path propertyPath = Paths.get("src/test/resources/TaskTwo/properties5.txt");

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Handler.loadFromProperties(cls, propertyPath);
        }, "There is no such file.");
        Assertions.assertEquals("There are some troubles with file.", exception.getMessage());
    }



}
