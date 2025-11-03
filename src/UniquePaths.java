/**
 * Unique Paths
 * There is an m x n grid where you are allowed to move either down or to the right at any point in time.
 *
 * Given the two integers m and n, return the number of possible unique paths
 * that can be taken from the top-left corner of the grid (grid[0][0])
 * to the bottom-right corner (grid[m - 1][n - 1]).
 *
 * You may assume the output will fit in a 32-bit integer.
 *
 * Example 1:
 * Input: m = 3, n = 6
 *
 * Output: 21
 * Example 2:
 *
 * Input: m = 3, n = 3
 *
 * Output: 6
 * Constraints:
 *
 * 1 <= m, n <= 100
 */
public class UniquePaths {
    public static void main(String[] args) {
        int m = 3;
        int n = 6;
        System.out.println(uniquePathsDfs(m,n));
        System.out.println(uniquePathsDfsMemo(m, n));
        System.out.println(uniquePathsDp(m, n));
        System.out.println(uniquePathsDpSpaceOptimized(m, n));
    }

    public static int uniquePathsDfs(int m, int n) {
        if (m == 1 || n == 1)
            return 1;
        return uniquePathsDfs(m-1, n) + uniquePathsDfs(m, n-1);
    }

    public static int uniquePathsDfsMemo(int m, int n) {
        int[][] cache = new int[m][n];
        return uniquePathsDfs(m-1, n-1, cache);
    }

    public static int uniquePathsDfs(int m, int n, int[][] cache) {
        if (m == 0 || n == 0)
            return 1;
        if (cache[m][n] != 0)
            return cache[m][n];

        cache[m][n] = uniquePathsDfs(m-1, n, cache) + uniquePathsDfs(m, n-1, cache);
        return cache[m][n];
    }

    public static int uniquePathsDp(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }

    public static int uniquePathsDpSpaceOptimized(int m, int n) {
        int[] dp = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[j] = 1;
                } else {
                    dp[j] = dp[j - 1] + dp[j];
                }
            }
        }
        return dp[n-1];
    }
}
