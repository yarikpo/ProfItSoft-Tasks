package TaskTwo;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class HandlerTest {

    @Test
    public void testHandler() {

        Class<ExampleClass> cls = ExampleClass.class;
        Path propertyPath = Paths.get("src/test/resources/TaskTwo/properties.txt");
        Handler.loadFromProperties(cls, propertyPath);
    }
}
