package TaskTwo;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

//        System.out.println(hashTagsRate);

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
        // here will be stored unique hashtags
        List<String> words = new ArrayList<>();
        // get strings which starts with ONE '#' character and may have letters after it
        Pattern pattern = Pattern.compile("#[\\w_]+");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            words.add(matcher.group());
        }
        // deleting empty and same elements
        return words.stream()
                .distinct()
                .filter(word -> !word.isEmpty())
                .toList();

    }

}
