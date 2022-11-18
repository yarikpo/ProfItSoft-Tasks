package TaskOne;

import java.io.*;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Handler {

    private static BufferedReader reader;
    private static BufferedWriter writer;
    // if in one line there are more than one person this variable will handle all people except first one other-vice it handles null
    private static String lastLine = null;
    private static boolean isClosedReader = false;

    /**
     * Creates copy of the XML file with merged name and surname
     * @param file
     * @throws IOException
     */
    public static void mergeNameSurname(Path file) throws IOException {

        writer = new BufferedWriter(new FileWriter(file.toAbsolutePath().getParent().toString() + "/copy_" + file.getFileName()));
        reader = new BufferedReader(new FileReader(file.toAbsolutePath().toString()));

        // cycle will be repeated until it is impossible to read
        while (!isClosedReader) {
            String text = personReader();
            text = replaceNameSurname(text);
            personCopyWriter(text);
        }

        writer.close();
    }

    /**
     * merges name and surname in paragraph with exactly maximum one person
     * @param text
     * @return String modified text
     */
    private static String replaceNameSurname(String text) {

        Pattern namePattern = Pattern.compile("(.*\\Wname\\s*=\\s*\")([^\"]*)(\".*)");
        Pattern surnamePattern = Pattern.compile("\\s*surname\\s*=\\s*\"([^\"]*)\"\\s*");

        Matcher matcher = namePattern.matcher(text);
        String beforeName, name, afterName, whatReplace;
        if (matcher.find()) {
            whatReplace = matcher.group();
            beforeName = matcher.group(1);
            name = matcher.group(2);
            afterName = matcher.group(3);
        }
        else {
            return text;
        }

        matcher = surnamePattern.matcher(text);
        String surname;
        if (matcher.find()) {
            surname = matcher.group(1);
        }
        else {
            return text;
        }

        text = text.replace(whatReplace, beforeName + name + " " + surname + afterName);
        text = text.replaceFirst("surname\\s*=\\s*\"[^\"]*\"", "");

        return text;
    }

    /**
     * Writes text into writer
     * @param text
     * @throws IOException
     */
    private static void personCopyWriter(String text) throws IOException {

        writer.write(text);
    }

    /**
     * Reads lines until close tag or end of the file
     * @return String text which has to finish with closed tag
     * @throws IOException
     */
    private static String personReader() throws IOException {

        StringBuilder result = new StringBuilder("");
        String line = "";

        // if in previous line there were more than one person
        if (lastLine != null) {

            if (appendLine(result, lastLine) == true) {
                return result.toString();
            }
            else {
                result.append("\n");
                lastLine = null;
            }
        }

        while((line = reader.readLine()) != null) {
            if (appendLine(result, line) == true) {
                return result.toString();
            }
            else {
                result.append(line);
                result.append("\n");
                lastLine = null;
            }
        }
        reader.close();
        isClosedReader = true;
        return result.toString();
    }

    /**
     * Appends peice of line to builder until finds closed tag
     * @param builder
     * @param line
     * @return false when there is no closed tag
     * @return true when there is closed tag
     */
    private static boolean appendLine(StringBuilder builder, String line) {

        int closeIndex = line.indexOf("/>");
        if (closeIndex == -1) return false;

        // appends until closed tag
        builder.append(line.substring(0, closeIndex + 2));
        // saves text after closed tag
        lastLine = line.substring(closeIndex + 2);

        return true;
    }

}
