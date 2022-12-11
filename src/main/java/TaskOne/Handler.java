package TaskOne;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class Handler {

    List<Future<Map<String, Double>>> futuresHashMaps;

    public void makeStatistics(File directory) throws InterruptedException {

        if (directory == null || directory.isDirectory() == false) {
            throw new IllegalArgumentException("Must be directory.");
        }

        ExecutorService executor = Executors.newSingleThreadExecutor();
        List<CallableFile> callableFiles = new ArrayList<>();
        File[] files = directory.listFiles();
        for (File file : files) {
            callableFiles.add(new CallableFile(file));
        }
        futuresHashMaps = executor.invokeAll(callableFiles);
    }


}
