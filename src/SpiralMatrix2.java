import java.util.Arrays;

/**
 * 59. Spiral Matrix II
 * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3
 * Output: [[1,2,3],[8,9,4],[7,6,5]]
 * Example 2:
 *
 * Input: n = 1
 * Output: [[1]]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 20
 */
public class SpiralMatrix2 {
    public static void main(String[] args) {
        int n = 3;
        int[][] result = generateSpiralMatrix(n);
        for (int[] row: result) {
            System.out.println(Arrays.toString(row));
        }
    }

    static int[][] generateSpiralMatrix(int n) {
        int[][] result = new int[n][n];
        int num = 1;
        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = n - 1;
        while (num <= n * n) {
            for (int i = left; i <= right; i++) {
                result[top][i] = num;
                num++;
            }
            top++;

            for (int i = top; i <= bottom; i++) {
                result[i][right] = num;
                num++;
            }
            right--;

            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result[bottom][i] = num;
                    num++;
                }
                bottom--;
            }

            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result[i][left] = num;
                    num++;
                }
                left++;
            }
        }
        return result;
    }
}
