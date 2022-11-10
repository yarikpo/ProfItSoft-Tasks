package TaskOne;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Handler {

    /**
     * Returns sorted and filtered from negative numbers array
     * @param array entire array
     * @return <code>long[]</code> if argument array is not NULL
     * @throws NullPointerException if argument array is NULL
     */
    public Long[] sortedPositiveArrayReceiver(Long[] array) {

        if (array == null) {
            throw new NullPointerException("Array can't be null.");
        }
        return Arrays.stream(array)
                .filter(val -> val >= 0L)
                .sorted(Comparator.reverseOrder())
                .toArray(Long[]::new);
    }


}
