/**
 * House Robber
 * You are given an integer array nums where nums[i] represents the amount of
 * money the ith house has. The houses are arranged in a straight line,
 * i.e. the ith house is the neighbor of the (i-1)th and (i+1)th house.
 *
 * You are planning to rob money from the houses, but you cannot rob
 * two adjacent houses because the security system will automatically alert the police
 * if two adjacent houses were both broken into.
 *
 * Return the maximum amount of money you can rob without alerting the police.
 *
 * Example 1:
 *
 * Input: nums = [1,1,3,3]
 *
 * Output: 4
 */
public class HouseRobber {
    public static void main(String[] args) {
        int[] nums = {1, 1, 3, 3};
        System.out.println(maxAmountOfMoney(nums));
    }

    private static int maxAmountOfMoney(int[] nums) {
       int n = nums.length;
       int[] dp = new int[n + 1];
       dp[0] = 0;
       dp[1] = nums[0];
       for (int i = 2; i <= n; i++) {
           dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
       }
       return dp[n];
    }
}
