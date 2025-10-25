import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Merge Overlapping Intervals
 * Given a list of intervals, return a new list with each set of overlapping intervals merged.
 *
 * If you have two intervals [a, b] and [c, d] where b == c then they do not overlap
 *
 * Input-Output
 *
 * Example 1
 *
 * Input: [[1, 4], [1, 5]]
 *
 * |--|--|--|--|--|--|--|--|--|--|--|--|--|--|
 * x========x
 * x===========x
 *
 * Output: [1, 5]
 *
 * |--|--|--|--|--|--|--|--|--|--|--|--|--|--|
 * x===========x
 */
public class MergedOverlappingIntervals {
    public static void main(String[] args) {
        int[][] input = {{1,4}, {1,5}, {8,10}};
        int[][] output = merge(input);
        for (int i = 0; i < output.length; i++)
            System.out.println(Arrays.toString(output[i]));
    }

    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        int[] prev = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (interval[0] <= prev[1]) {
                prev[1] = Math.max(prev[1], interval[1]);
            } else {
                merged.add(prev);
                prev = interval;
            }
        }

        merged.add(prev);

        return merged.toArray(new int[merged.size()][]);
    }
}
