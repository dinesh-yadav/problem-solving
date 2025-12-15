import java.util.Arrays;

/**
 * Set Matrix Zeroes
 * Solved
 * Given an m x n matrix of integers matrix, if an element is 0, set its entire row and column to 0's.
 *
 * You must update the matrix in-place.
 *
 * Follow up: Could you solve it using O(1) space?
 *
 * Example 1:
 * Input: matrix = [
 *   [1,2,3],
 *   [4,0,5],
 *   [6,7,8]
 * ]
 *
 * Output: [
 *   [1,0,3],
 *   [0,0,0],
 *   [6,0,8]
 * ]
 * Constraints:
 *
 * 1 <= matrix.length, matrix[0].length <= 100
 * -2^31 <= matrix[i][j] <= (2^31) - 1
 */
public class SetMatrixZero {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 0, 5},
                {6, 7, 8}
        };
//        setMatrixZero(matrix);
        setMatrixZeroSpaceOptimized(matrix);
        for (int[] row: matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static void setMatrixZero(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[] markRows = new boolean[rows];
        boolean[] markCols = new boolean[cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == 0) {
                    markRows[row] = true;
                    markCols[col] = true;
                }
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (markRows[row] || markCols[col]) {
                    matrix[row][col] = 0;
                }
            }
        }
    }

    private static void setMatrixZeroSpaceOptimized(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean firstRow = false;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == 0) {
                   matrix[row][0] = 0;
                   if (row > 0) {
                       matrix[0][col] = 0;
                   } else {
                       firstRow = true;
                   }
                }
            }
        }

        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < cols; col++) {
                if (matrix[0][col] == 0 || matrix[row][0] == 0) {
                    matrix[row][col] = 0;
                }
            }
        }

        if (matrix[0][0] == 0) {
            for (int row = 0; row < rows; row++) {
                matrix[row][0] = 0;
            }
        }

        if (firstRow) {
            for (int col = 0; col < cols; col++) {
                matrix[0][col] = 0;
            }
        }
    }
}
/*
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean firstRow = false;
        boolean firstCol = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0)
                        firstRow = true;
                    if (j == 0)
                        firstCol = true;
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

         for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
         }

         if (firstRow) {
            for (int i = 0; i < n; i++) {
                matrix[0][i] = 0;
            }
         }

         if (firstCol) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
         }

    }
}
 */