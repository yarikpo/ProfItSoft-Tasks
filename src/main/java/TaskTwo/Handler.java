package TaskTwo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Handler {

    private static Map<String, String> properties = new HashMap<>();

    public static <T> T loadFromProperties(Class<T> cls, Path propertiesPath) {

        T resultClass;

        try {
            Constructor<T> constructor = cls.getConstructor();
            resultClass = constructor.newInstance();
        }
        catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

        fillProperties(properties, propertiesPath);
        System.out.println(properties);
        fillObjectWithProperties(resultClass);


        return resultClass;
    }

    private static <T> void fillObjectWithProperties(T object) {

        for (Map.Entry<String, String> entry : properties.entrySet()) {
            fillObjectWithProperty(object, entry.getKey(), entry.getValue());
        }
    }

    private static <T> void fillObjectWithProperty(T object, String key, String value) {

        
    }

    private static void fillProperties(Map<String, String> properties, Path propertiesPath) {

        try (BufferedReader bf = new BufferedReader(new FileReader(propertiesPath.toFile()))) {
            String line = "";
            while ((line = bf.readLine()) != null) {
                String[] keyAndVal = line.split("\\s*=\\s*");
                if (keyAndVal.length != 2) {
                    throw new IllegalArgumentException("Troubles with property file content.");
                }
                properties.put(keyAndVal[0], keyAndVal[1]);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("There are some troubles with file - " + propertiesPath.toString());
        }
    }

}
