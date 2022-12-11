package TaskOne;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Handler {

    private static List<Future<Map<String, Double>>> futuresHashMaps;
    private static Map<String, Double> finesByTypes;

    /**
     * function gets directory and number of threads and makes json file with statistics out of XML files
     * @param directory
     * @param threads
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public static void makeStatistics(File directory, int threads) throws InterruptedException, ExecutionException, IOException {

        finesByTypes = new HashMap<>();
        if (directory == null || directory.isDirectory() == false) {
            throw new IllegalArgumentException("Must be directory.");
        }

        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<CallableFile> callableFiles = new ArrayList<>();
        File[] files = directory.listFiles();
        for (File file : files) {
            callableFiles.add(new CallableFile(file));
        }
        futuresHashMaps = executor.invokeAll(callableFiles);
        updateFuturesToMap();

        finesByTypes = makeSortedMap(finesByTypes);
        System.out.println(finesByTypes);
        convertMap2JSON(finesByTypes);
    }

    /**
     * Converts map into JSON file
     * @param finesByTypes
     * @throws IOException
     */
    public static void convertMap2JSON(Map<String, Double> finesByTypes) throws IOException {

        try (JsonGenerator generator =
                     CallableFile.getDefaultMapper().getFactory().createGenerator(
                             new File("src/test/resources/TaskOne/JSON_FILES/result.json"),
                             JsonEncoding.UTF8
                     )) {

            generator.writeStartObject();
            generator.writeFieldName("fines");
            generator.writeStartArray();

            for (Map.Entry<String, Double> fineByType : finesByTypes.entrySet()) {
                generator.writeStartObject();
                generator.writeStringField("type", fineByType.getKey());
                generator.writeNumberField("fine_amount", Math.ceil(fineByType.getValue() * 100.0) / 100.0);
                generator.writeEndObject();
            }

            generator.writeEndArray();
            generator.writeEndObject();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * Updates map with result by adding values from map from the Future variable
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void updateFuturesToMap() throws ExecutionException, InterruptedException {

        for (Future<Map<String, Double>> futureHashMap : futuresHashMaps) {
            Map<String, Double> map = futureHashMap.get();
            for (Map.Entry<String, Double> entry : map.entrySet()) {
                String key = entry.getKey();
                Double value = entry.getValue();
                finesByTypes.put(key, finesByTypes.getOrDefault(key, 0.0) + value);
            }
        }
    }


    /**
     * Sorts map by double value
     * @param finesByTypes
     * @return sorted Map
     */
    public static Map<String, Double> makeSortedMap(Map<String, Double> finesByTypes) {

        return finesByTypes.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> y, LinkedHashMap::new));
    }


}
