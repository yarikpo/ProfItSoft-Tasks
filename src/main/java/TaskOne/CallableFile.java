package TaskOne;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class CallableFile implements Callable<Map<String, Double>> {

    private File file;
    private Map<String, Double> finesByTypes = new HashMap<>();
    private static final SAXParserFactory factory = SAXParserFactory.newInstance();
    private final SAXHandler handler = new SAXHandler(this.finesByTypes);
    private SAXParser parser;
    private static final ObjectMapper DEFAULT_MAPPER = new ObjectMapper();

    public CallableFile(File file) {
        this.file = file;
    }

    @Override
    public Map<String, Double> call() throws Exception {

        readDataFromFile(this.file);

        return this.finesByTypes;
    }


    /**
     * Parses each file
     * @param file
     */
    public void readDataFromFile(File file) {

        try {
            parser = factory.newSAXParser();
            parser.parse(file, handler);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Something wrong with parser.");
        }
    }

    public static ObjectMapper getDefaultMapper() {
        return DEFAULT_MAPPER;
    }

}
