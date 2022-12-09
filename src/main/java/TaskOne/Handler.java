package TaskOne;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Handler {

    private static final SAXParserFactory factory = SAXParserFactory.newInstance();
    private static final SAXHandler handler = new SAXHandler();
    private static SAXParser parser;
    private static final ObjectMapper DEFAULT_MAPPER;

    static {

        ObjectMapper mapper = new ObjectMapper();

        DEFAULT_MAPPER = mapper;
    }


    public static void main(String[] args) throws IOException {

        File directory = new File("src/test/resources/TaskOne/XML_FILES");
        System.out.println(directory.getAbsoluteFile());
        Arrays.stream(directory.list()).forEach(System.out::println);

        readDataFromFolder(directory);
        convertMap2JSON(makeSortedMap(handler.getFinesByTypes()));
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

    /**
     * Converts map into JSON file
     * @param finesByTypes
     * @throws IOException
     */
    public static void convertMap2JSON(Map<String, Double> finesByTypes) throws IOException {

        try (JsonGenerator generator =
                DEFAULT_MAPPER.getFactory().createGenerator(
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
     * Goes through all files from folder
     * @param directory
     */
    public static void readDataFromFolder(File directory) {

        if (directory == null || directory.isDirectory() == false) {
            throw new IllegalArgumentException("Must be directory.");
        }

        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.getName().toLowerCase().endsWith(".xml") == false) continue;

            readDataFromFile(file);
        }
    }

    /**
     * Parses each file
     * @param file
     */
    public static void readDataFromFile(File file) {

        try {
            parser = factory.newSAXParser();
            parser.parse(file, handler);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Something wrong with parser.");
        }
    }


}
