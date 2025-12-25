import java.util.Arrays;
import java.util.Stack;

/**
 * 221. Maximal Square
 * Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 4
 * Example 2:
 *
 *
 * Input: matrix = [["0","1"],["1","0"]]
 * Output: 1
 * Example 3:
 *
 * Input: matrix = [["0"]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] is '0' or '1'.
 */
public class MaximalSquare {
    public static void main(String[] args) {
        char[][] matrix = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}};
        System.out.println(maxAreaOfSquareDp(matrix));
        System.out.println(maxAreaOfSquareHistogramAndStack(matrix));
        System.out.println(maxAreaOfSquareMemoization(matrix));
    }

    static int maxAreaOfSquareDp(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows + 1][cols + 1];
        int maxSide = 0;
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= cols; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else {
                    if (matrix[i - 1][j - 1] == '1') {
                        dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                    } else {
                        dp[i][j] = 0;
                    }
                    maxSide = Math.max(dp[i][j], maxSide);
                }
            }
        }
        return maxSide * maxSide;
    }

    static int maxS = 0;
    static int[][] memo;
    static int maxAreaOfSquareMemoization(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        memo = new int[rows + 1][cols + 1];
        for (int[] r : memo) {
            Arrays.fill(r, -1);
        }
        dfs(matrix, 0, 0);
        return maxS * maxS;
    }

    static int dfs(char[][] matrix, int i, int j) {
        if (i >= matrix.length || j >= matrix[0].length) {
            return 0;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int right = dfs(matrix, i, j + 1);
        int down = dfs(matrix, i + 1, j);
        int diag = dfs(matrix, i + 1, j + 1);
        if (matrix[i][j] == '1') {
            memo[i][j] = 1 + Math.min(right, Math.min(down, diag));
            maxS = Math.max(maxS, memo[i][j]);
        } else {
            memo[i][j] = 0;
        }

        return memo[i][j];
    }

    static int maxAreaOfSquareHistogramAndStack(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] heights = new int[cols];
        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                heights[j] = (matrix[i][j] == '1')? heights[j] + 1 : 0;
            }
            int area = maxSquare(heights);
            maxArea = Math.max(area, maxArea);
        }

        return maxArea == Integer.MIN_VALUE ? 0 : maxArea;
    }

    static int maxSquare(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i <= n; i++) {
            int currH = (i == n) ? 0 : heights[i];
            while(!stack.isEmpty() && currH < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = 0;
                if (stack.isEmpty()) {
                    width = i;
                } else {
                    width = i - stack.peek() - 1;
                }

                int side = Math.min(height, width);
                maxArea = Math.max(maxArea, side * side);
            }
            stack.push(i);
        }
        return maxArea;
    }
}
