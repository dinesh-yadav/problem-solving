import java.util.Stack;

/**
 * 85 Maximum Rectangle
 * Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 6
 * Explanation: The maximal rectangle is shown in the above picture.
 * Example 2:
 *
 * Input: matrix = [["0"]]
 * Output: 0
 * Example 3:
 *
 * Input: matrix = [["1"]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * rows == matrix.length
 * cols == matrix[i].length
 * 1 <= rows, cols <= 200
 * matrix[i][j] is '0' or '1'.
 *
 */
public class MaximumRectangle {
    public static void main(String[] args) {
        char[][] matrix = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}};
        System.out.println(maxAreaOfRectangle(matrix));
    }

    static int maxAreaOfRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int maxArea = Integer.MIN_VALUE;
        int[] heights = new int[cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                heights[j] = (matrix[i][j] == '0') ? 0 : heights[j] + 1;
            }
            int area = maxHistogramArea(heights);
            maxArea = Math.max(area, maxArea);
        }
        return (maxArea == Integer.MIN_VALUE) ? 0 : maxArea;
    }

    static int maxHistogramArea(int[] heights) {
        if (heights == null || heights.length == 0)
            return 0;
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];
            while(!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
               int height = heights[stack.pop()];
               int width = 0;
               if (stack.isEmpty()) {
                   width = i;
               } else {
                   width = i - stack.peek() - 1;
               }
               maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }

        return (maxArea == Integer.MIN_VALUE) ? 0 : maxArea;
    }
}
