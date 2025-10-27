/**
 * Maximum Subarray
 * Given an array of integers nums, find the subarray with the largest sum and return the sum.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * Example 1:
 *
 * Input: nums = [2,-3,4,-2,2,1,-1,4]
 *
 * Output: 8
 * Explanation: The subarray [4,-2,2,1,-1,4] has the largest sum 8.
 *
 * Example 2:
 *
 * Input: nums = [-1]
 *
 * Output: -1
 * Constraints:
 * 1 <= nums.length <= 1000
 * -1000 <= nums[i] <= 1000
 */
public class MaximumSubArray {
    public static void main(String[] args) {
        //int[] nums = {2,-3,4,-2,2,1,-1,4};
        int[] nums = {-3, -2, -1, 1};
        System.out.println(maxSubArraySum(nums));
    }

    // Kadane's Algorithm
    public static int maxSubArraySum(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int num: nums) {
            if (sum < 0)
                sum = 0;
            sum += num;
            maxSum = Math.max(sum, maxSum);
        }
        return maxSum;
    }
}
