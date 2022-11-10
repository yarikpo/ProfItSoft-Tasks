package TaskTwo;

import java.util.*;
import java.util.stream.Collectors;

public class Handler {

    /**
     * Returns top five most frequent hashtags from multiple line (counts unique hashtags from each line)
     * @param lines List of Strings
     * @return Map if lines does not equal to NULL
     * @throws NullPointerException when lines equals to NULL
     */
    public Map<String, Integer> receiveTopFiveHashTags(List<String> lines) {

        if (lines == null) {
            throw new NullPointerException("List can not be null.");
        }

        Map<String, Integer> hashTagsRate = calculateHashTagsRate(lines);

        Set<Map.Entry<String, Integer>> sortedHashTagsRate = receiveSortedHashTagsRate(hashTagsRate);

        Map<String, Integer> topFiveHashTagsRate = new TreeMap<>(Comparator.reverseOrder());
        for(Map.Entry<String, Integer> hashTag : sortedHashTagsRate) {
            if (topFiveHashTagsRate.size() > 4) break;
            topFiveHashTagsRate.put(hashTag.getKey(), hashTag.getValue());
        }

        return topFiveHashTagsRate;
    }

    /**
     * Converts Map to Set to get sorted Map by values
     * @param hashTagsRate
     * @return Set sorted set
     */
    private Set<Map.Entry<String, Integer>> receiveSortedHashTagsRate(Map<String, Integer> hashTagsRate) {
        Set<Map.Entry<String, Integer>> sortedEntries = new TreeSet<>(
                new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {

                        int res = -o1.getValue().compareTo(o2.getValue());
                        return res != 0 ? res : 1;
                    }
                }
        );
        sortedEntries.addAll(hashTagsRate.entrySet());
        return sortedEntries;
    }

    /**
     * Counts number of each hashtag in different lines
     * @param lines List of Strings
     * @return Map with number of appearance of each hashtag
     */
    private Map<String, Integer> calculateHashTagsRate(List<String> lines) {

        Map<String, Integer> hashTagsRate = new TreeMap<>();
        for (String line : lines) {
            List<String> hashTags = receiveUniqueHashTagsFromLine(line);
            // increase hashtag rate by one
            hashTags.stream()
                    .forEach(hashTag -> hashTagsRate.put(hashTag, hashTagsRate.getOrDefault(hashTag, 0) + 1));
        }

        return hashTagsRate;
    }

    /**
     * Finds hashtags in line
     * @param line String
     * @return List of Strings
     */
    private List<String> receiveUniqueHashTagsFromLine(String line) {
        // gets all words out of line
        List<String> words = List.of(line.split("[\s\n,\\.\\t!?]+"));

        // separates hashtags like "#f1#f2## into different ones
        List<String> wordsWithSeparatedHashTags = new ArrayList<>();
        for (String word : words) {
            String newWord = word;
            // deletes first part of the word if it is not a hashtag
            if (!word.startsWith("#")) newWord = deleteUntilSymbol(word, '#');
            newWord = newWord.trim();
            if (newWord.isEmpty()) continue;
            wordsWithSeparatedHashTags.addAll(List.of(newWord.split("[#]+")));

        }

        // get unique hashtags
        Set<String> uniqueHashTags = wordsWithSeparatedHashTags.stream()
                .filter(word -> !word.isEmpty())
                .collect(Collectors.toSet());

        return new ArrayList<>(uniqueHashTags);
    }

    /**
     * Deletes symbols in word until symbol variable comes
     * @param word entire word
     * @param symbol symbol before which's first appearance substring has to be deleted
     * @return String with deleted substring or empty string if there were no symbol appearance
     */
    private String deleteUntilSymbol(String word, char symbol) {

        StringBuffer builder = new StringBuffer(word);
        int end = builder.indexOf("#");
        if (end == -1) return "";
        builder.delete(0, end);
        return builder.toString();
    }

}
