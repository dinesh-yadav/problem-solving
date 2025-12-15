import java.util.*;

public class EqualValuePairs {

    public static long[] countPairs(int n, int[][] queries) {
        int[] arr = new int[n];
        Map<Integer, Long> freq = new HashMap<>();
        long pairCount = 0;

        // Initially all values are 0
        freq.put(0, (long) n);
        pairCount = (long) n * (n - 1) / 2;

        long[] result = new long[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int newVal = queries[i][1];
            int oldVal = arr[index];

            if (oldVal != newVal) {
                // Remove old value contribution
                long oldFreq = freq.get(oldVal);
                pairCount -= (oldFreq - 1);
                freq.put(oldVal, oldFreq - 1);

                // Add new value contribution
                long newFreq = freq.getOrDefault(newVal, 0L);
                pairCount += newFreq;
                freq.put(newVal, newFreq + 1);

                arr[index] = newVal;
            }

            result[i] = pairCount;
        }

        return result;
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] queries = {{0,1},{1,2},{2,2},{0,2},{1,1}};
        System.out.println(Arrays.toString(countPairs(n, queries)));
    }
}
