import java.util.Arrays;
/**
 * 16. 3Sum Closest
 * Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
 *
 * Return the sum of the three integers.
 *
 * You may assume that each input would have exactly one solution.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
public class ThreeSumClosest {
    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, -4};
        int target = 1;
        System.out.println(threeSumClosest(nums, target));
    }

    static public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        int minSum = Integer.MAX_VALUE;
        int minClosestSum = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            int l = i + 1;
            int r = n - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                int closestSum = Math.abs(target - sum);
                // System.out.println("i:" + i +" sum: " + sum + " closestSum: " + closestSum);
                if (closestSum < minClosestSum) {
                    minClosestSum = closestSum;
                    minSum = sum;
                }
                if (sum == target) {
                    return sum;
                } else if(sum < target) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return minSum;
    }
}
