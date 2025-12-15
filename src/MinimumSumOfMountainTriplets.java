/**
 * 2908. Minimum Sum of Mountain Triplets I
 * Solved
 * Easy
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * You are given a 0-indexed array nums of integers.
 *
 * A triplet of indices (i, j, k) is a mountain if:
 *
 * i < j < k
 * nums[i] < nums[j] and nums[k] < nums[j]
 * Return the minimum possible sum of a mountain triplet of nums. If no such triplet exists, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [8,6,1,5,3]
 * Output: 9
 * Explanation: Triplet (2, 3, 4) is a mountain triplet of sum 9 since:
 * - 2 < 3 < 4
 * - nums[2] < nums[3] and nums[4] < nums[3]
 * And the sum of this triplet is nums[2] + nums[3] + nums[4] = 9. It can be shown that there are no mountain triplets with a sum of less than 9.
 */
public class MinimumSumOfMountainTriplets {
    public static void main(String[] args) {
        int[] nums = {8, 6, 1, 5, 3};
        System.out.println(minimumSum(nums));
        System.out.println(minimumSumOptimized(nums));
    }

    static public int minimumSum(int[] nums) {
        int n = nums.length;
        int minSum = Integer.MAX_VALUE;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] < nums[j] && nums[k] < nums[j]) {
                        minSum = Math.min(minSum, nums[i] + nums[j] + nums[k]);
                    }
                }
            }
        }
        return minSum == Integer.MAX_VALUE?-1:minSum;
    }

    static public int minimumSumOptimized(int[] nums) {
        int n = nums.length;
        int[] l = new int[n];
        int[] r = new int[n];
        l[0] = nums[0];
        r[n - 1] = nums[n - 1];

        for (int i = 1; i < n; i++) {
            l[i] = Math.min(l[i - 1], nums[i]);
            r[n - 1 - i] = Math.min(r[n - i], nums[n - i]);
        }

        int minSum = Integer.MAX_VALUE;
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > l[i] && nums[i] > r[i]) {
                minSum = Math.min(minSum, nums[i] + l[i] + r[i]);
            }
        }
        return minSum;
    }
}
