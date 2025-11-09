/**
 * Maximum Product Subarray
 * Solved
 * Given an integer array nums, find a subarray that has the largest product within the array and return it.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * You can assume the output will fit into a 32-bit integer.
 *
 * Example 1:
 *
 * Input: nums = [1,2,-3,4]
 *
 * Output: 4
 * Example 2:
 *
 * Input: nums = [-2,-1]
 *
 * Output: 2
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * -10 <= nums[i] <= 10
 */
public class MaximumProductSubArray {
    public static void main(String[] args) {
        int[] nums = {1, 2, -3, 4};
        System.out.println(maximumProductSubArray(nums));
        System.out.println(maxProductSubArray(nums));
    }

    // Dynamic programming
    // Kadane's Algorithm Time O(n), space O(1)
    private static int maximumProductSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxProduct = nums[0];
        int max = 1;
        int min = 1;
        for (int num: nums) {
            int tmp = max * num; // save the max value before updating it.
            max = Math.max(Math.max(max * num, min * num), num);
            min = Math.min(Math.min(tmp, min * num), num);

            maxProduct = Math.max(maxProduct, max);
        }
        return maxProduct;
    }

    // prefix, suffix
    private static int maxProductSubArray(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int maxProduct = nums[0];
        int prefix = 0;
        int suffix = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            prefix = nums[i] * (prefix == 0? 1 : prefix);
            suffix = nums[n - i - 1] * (suffix == 0? 1 : suffix);
            maxProduct = Math.max(maxProduct, Math.max(prefix, suffix));
        }
        return maxProduct;
    }
}

