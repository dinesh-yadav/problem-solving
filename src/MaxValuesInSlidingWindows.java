import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * /*
 * * Given an array nums and an integer k, return the maximum value in each sliding window of size k.
 *
 * An array of numbers: nums = [1,3,-1,-3,5,3,6,7]
 * A window size k (e.g., 3)
 * [7, 3, 3, 5, 5, 6, 7]
 */
public class MaxValuesInSlidingWindows {
    public static void main(String[] args) {
        int[] nums = {7, 3, 3, 5, 5, 6, 7};//{1,3,-1,-3,5,3,6,7};
        int k = 3;
        System.out.println(Arrays.toString(maxValuesInSlidingWindows(nums, k)));
        System.out.println(Arrays.toString(maxValuesUsingPriorityQueue(nums, k)));
        System.out.println(Arrays.toString(maxValuesOptimized(nums, k)));
    }

    public static int[] maxValuesInSlidingWindows(int[] nums, int k) {
        if (nums == null || nums.length == 1) {
            return new int[0];
        }
        int len = nums.length;
        int[] result = new int[len - k + 1];

        for (int i = 0; i < len - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < k + i; j++) {
                max = Math.max(max, nums[j]);
            }
            result[i] = max;
        }
        return result;
    }

    public static int[] maxValuesUsingPriorityQueue(int[] nums, int k) {
        if (nums == null || nums.length == 1) {
            return new int[0];
        }
        int len = nums.length;
        int[] result = new int[len - k + 1];

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        int j = 0;
        for (int i = 0; i < len; i++) {
            pq.add(nums[i]);
            if (pq.size() == k) {
                result[j] = pq.peek();
                pq.remove(nums[j]);
                j++;
            }
        }
        return result;
    }

    public static int[] maxValuesOptimized(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
            return new int[0];
        }

        int n = nums.length;
        int[] result = new int[n - k + 1];
        int resIndex = 0;

        // this doubly ended queue will save the index of elements.
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            // remove the elements from the end
            // if they are smaller than the current element
            while(!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]) {
                dq.removeLast();
            }

            // remove the elements which are out of the sliding window
            while (!dq.isEmpty() && dq.peekFirst() < i - k + 1) {
                dq.removeFirst();
            }


            dq.addLast(i);

            if (i >= k - 1) {
                result[resIndex] = nums[dq.peekFirst()];
                resIndex++;
            }
        }
        return result;
    }
}
