package TaskOne;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HandlerTest {

    @Test
    public void test() throws IOException {
        // this test is for running TaskOne
        Path file = Paths.get("src/test/resources/TaskOne/People.xml");
        Handler.mergeNameSurname(file);
    }

}
