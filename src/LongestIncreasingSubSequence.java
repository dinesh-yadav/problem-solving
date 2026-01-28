import java.util.ArrayList;
import java.util.List;

/**
 * 300. Longest Increasing Subsequence
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 * Example 1:
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Example 2:
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 * Example 3:
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 * Constraints:
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 */
public class LongestIncreasingSubSequence {
    public static void main(String[] args) {
        int[] arr = {10,9,2,5,3,7,101,18};
        System.out.println(longestIncreasingSubSequence(arr));
        System.out.println(lengthOfLIS(arr));
    }

    // dynamic programming
    // time complexity O(nlogn)
    static int longestIncreasingSubSequence(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int[] result = new int[nums.length];
        int size = 0;

        for (int n : nums) {
            int i = 0, j = size;
            // Binary search to find the replacement index
            while (i != j) {
                int m = (i + j) / 2;
                if (result[m] < n)
                    i = m + 1;
                else
                    j = m;
            }
            result[i] = n;
            if (i == size)
                size++;
        }
        return size;
    }

    // time O(n^2)
    static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        int[] dp = new int[n];
        int overallMax = 1;

        // Step 1: Every element is an LIS of length 1
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        // Step 2: Compare each element with all elements before it
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    // If current number is greater, we can extend the sequence
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // Keep track of the highest value found in the dp array
            overallMax = Math.max(overallMax, dp[i]);
        }

        return overallMax;
    }
}
