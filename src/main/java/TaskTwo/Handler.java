package TaskTwo;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Arrays;
import java.util.Map;

public class Handler {

    private static final SAXParserFactory factory = SAXParserFactory.newInstance();
    private static final SAXHandler handler = new SAXHandler();
    private static SAXParser parser;


    public static void main(String[] args) {

        File directory = new File("src/test/resources/TaskTwo/XML_FILES");
        System.out.println(directory.getAbsoluteFile());
        Arrays.stream(directory.list()).forEach(System.out::println);

        readDataFromFolder(directory);
    }

    public static void convertMap2JSON(Map<String, Double> finesByTypes) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        JsonFactory factory = new JsonFactory();

    }

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
