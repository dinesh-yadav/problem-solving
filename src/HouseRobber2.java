/**
 * House Robber II
 * You are given an integer array nums where nums[i] represents the amount of money
 * the ith house has. The houses are arranged in a circle,
 * i.e. the first house and the last house are neighbors.
 *
 * You are planning to rob money from the houses,
 * but you cannot rob two adjacent houses because the security system will automatically
 * alert the police if two adjacent houses were both broken into.
 *
 * Return the maximum amount of money you can rob without alerting the police.
 *
 * Example 1:
 *
 * Input: nums = [3,4,3]
 *
 * Output: 4
 * Explanation: You cannot rob nums[0] + nums[2] = 6 because nums[0] and nums[2] are adjacent houses.
 * The maximum you can rob is nums[1] = 4.
 */
public class HouseRobber2 {

    public static void main(String[] args) {
        int[] nums = {3, 4, 3};
        System.out.println(maxAmount(nums));
    }

    private static int maxAmount(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return 0;
        if (n == 1)
            return nums[0];

        int[][] memo = new int[n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                memo[i][j] = -1;
            }
        }
        return Math.max(dfs(0, 1, nums, memo), dfs(1, 0, nums, memo));
    }

    private static int dfs(int index, int flag, int[] nums, int[][] memo) {
        //base condition
        int n = nums.length;
        if (index >= n || (flag == 1 && index == n - 1))
            return 0;
        if (memo[index][flag] != -1)
            return memo[index][flag];

        memo[index][flag] = Math.max(dfs(index + 1, flag, nums, memo),
                                    nums[index] + dfs(index + 2, flag |  (index == 0 ? 1 : 0), nums, memo));
        return memo[index][flag];
    }
}
/*
class Solution {
    public int rob(int[] nums) {
        if(nums.length < 2) {
            return nums[0];
        }

        int[] ignoreLast = new int[nums.length - 1];
        int[] ignoreFirst = new int[nums.length - 1];

        for(int i = 0; i<nums.length -1; i++) {
            ignoreLast[i] = nums[i];
            ignoreFirst[i] = nums[i+1];
        }

        return Math.max(robberHelper(ignoreFirst), robberHelper(ignoreLast));
    }


    int robberHelper(int[] arr) {
        if(arr.length < 2) {
            return arr[0];
        }

        int dp[] = new int[arr.length];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);

        for(int i = 2; i<arr.length; i++) {
            dp[i] = Math.max(arr[i] + dp[i-2], dp[i-1]);
        }

        return dp[arr.length - 1];
    }
}
 */