import java.util.Arrays;

/**
 * Climbing Stairs
 * You are given an integer n representing the number of steps to reach the top of a staircase.
 * You can climb with either 1 or 2 steps at a time.
 *
 * Return the number of distinct ways to climb to the top of the staircase.
 *
 * Example 1:
 *
 * Input: n = 2
 *
 * Output: 2
 * Explanation:
 *
 * 1 + 1 = 2
 * 2 = 2
 */
public class ClimbingStairs {
    public static void main(String[] args) {
        int n = 5;
        System.out.println(waysToClimbingStairs(n));
        System.out.println(waysToClimbingStairsWithCaching(n));
        System.out.println(waysToClimbingStairsDP(n));
        System.out.println(waysToClimbingStairsDPSpaceOptimization(n));
    }

    private static int waysToClimbingStairs(int n) {
        return helper(n, 0);
    }

    private static int helper(int n, int current) {
        if (current == n)
            return 1;
        if (current > n)
            return 0;
        return helper(n, current + 1) + helper(n, current + 2);
    }

    private static int waysToClimbingStairsWithCaching(int n) {
        int[] cache = new int[n];
        Arrays.fill(cache, -1);
        return helperWithCaching(n, 0, cache);
    }

    private static int helperWithCaching(int n, int current, int[] cache) {
        if (current == n)
            return 1;
        if (current > n)
            return 0;
        if (cache[current] != -1)
            return cache[current];
        cache[current] = helper(n, current + 1) + helper(n, current + 2);
        return cache[current];
    }

    private static int waysToClimbingStairsDP(int n) {
        if (n < 2)
            return 1;
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n-1];
    }

    private static int waysToClimbingStairsDPSpaceOptimization(int n) {
        if (n < 2)
            return 1;
        int dp1 = 1;
        int dp2 = 2;
        for (int i = 2; i < n; i++) {
            int tmp = dp2;
            dp2 = dp1 + dp2;
            dp1 = tmp;
        }
        return dp2;
    }
}
