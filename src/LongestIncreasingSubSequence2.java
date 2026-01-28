import java.util.Arrays;

/**
 * 2407. Longest Increasing Subsequence II
 * You are given an integer array nums and an integer k.
 *
 * Find the longest subsequence of nums that meets the following requirements:
 *
 * The subsequence is strictly increasing and
 * The difference between adjacent elements in the subsequence is at most k.
 * Return the length of the longest subsequence that meets the requirements.
 *
 * A subsequence is an array that can be derived from another array
 * by deleting some or no elements without changing the order of the remaining elements.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,2,1,4,3,4,5,8,15], k = 3
 * Output: 5
 * Explanation:
 * The longest subsequence that meets the requirements is [1,3,4,5,8].
 * The subsequence has a length of 5, so we return 5.
 * Note that the subsequence [1,3,4,5,8,15] does not meet the requirements because 15 - 8 = 7 is larger than 3.
 * Example 2:
 *
 * Input: nums = [7,4,5,1,8,12,4,7], k = 5
 * Output: 4
 * Explanation:
 * The longest subsequence that meets the requirements is [4,5,8,12].
 * The subsequence has a length of 4, so we return 4.
 * Example 3:
 *
 * Input: nums = [1,5], k = 1
 * Output: 1
 * Explanation:
 * The longest subsequence that meets the requirements is [1].
 * The subsequence has a length of 1, so we return 1.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i], k <= 105
 */
public class LongestIncreasingSubSequence2 {
    public static void main(String[] args) {
        int[] nums = {7,4,5,1,8,12,4,7};
        int k = 5;
        System.out.println(longestSubSequence(nums, k));
        System.out.println(longestSubSequenceOpti(nums, k));
    }

    // time O(n^2)
    static int longestSubSequence(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return 0;

        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxSize = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && (nums[i] - nums[j]) <= k) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxSize = Math.max(maxSize, dp[i]);
        }
        return maxSize;
    }

    // time O(n^k)
    static int longestSubSequenceOpti(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return 0;

        int n = nums.length;
        int[] dp = new int[100001];
        int maxSize = 1;

        for (int i = 0; i < n; i++) {
            for (int j = Math.max(0, nums[i] - k); j < nums[i]; j++) {
                dp[nums[i]] = Math.max(dp[nums[i]], dp[j] + 1);
            }
            maxSize = Math.max(maxSize, dp[nums[i]]);
        }
        return maxSize;
    }
}
