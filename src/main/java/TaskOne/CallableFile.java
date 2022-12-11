package TaskOne;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class CallableFile implements Callable<Map<String, Double>> {

    private File file;

    public CallableFile(File file) {
        this.file = file;
    }

    @Override
    public Map<String, Double> call() throws Exception {
        return new HashMap<>();
    }

}
