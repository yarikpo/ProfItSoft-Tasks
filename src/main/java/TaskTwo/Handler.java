package TaskTwo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.*;

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

        List<Field> fieldsToChange = new ArrayList<>();

        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Property.class)) {
                if (field.getAnnotation(Property.class).name().isEmpty()) {
                    if (field.getName().equals(key)) {
                        fieldsToChange.add(field); // when there is annotation, but there is no name property and var name equals to key
                    }
                }
                if (field.getAnnotation(Property.class).name().equals(key)) {
                    fieldsToChange.add(field); // when there is annotation where name equals key
                }
            }
            else {
                if (field.getName().equals(key)) {
                    fieldsToChange.add(field); // when there is no annotation and var name equals to key
                }
            }
        }

        if (fieldsToChange.size() != 1) {
            throw new IllegalStateException("File content is incorrect.");
        }

        Field fieldToChange = fieldsToChange.get(0);
        changeField(object, fieldToChange, value);
    }

    private static <T> void changeField(T object, Field field, String value) {

        if (field.getType() == Integer.class || field.getType() == int.class) {
            try {
                int realVal = Integer.parseInt(value);
                field.setAccessible(true);
                field.set(object, realVal);
            }
            catch (NumberFormatException e) {
                e.printStackTrace();
                throw new IllegalArgumentException("" + field.getName() + " must be an " + field.getType() + " in properties file.");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
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
