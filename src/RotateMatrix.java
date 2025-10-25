import java.util.Arrays;

/**
 * Given an nxn two-dimensional matrix of numbers, rotate the matrix 90 degrees to the right (clockwise).
 *
 * Input-Output
 *
 * Example 1
 *
 * In
 *
 * [
 *   [ 1,  2,  3, 4],
 *   [ 5,  6,  7, 8],
 *   [ 9, 10, 11, 12],
 *   [13, 14, 15, 16]
 * ]
 * Out
 *
 * [
 *  [13,  9, 5, 1],
 *  [14, 10, 6, 2],
 *  [15, 11, 7, 3],
 *  [16, 12, 8, 4]
 * ]
 */

public class RotateMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16}
        };
        rotateMatrix1(matrix);
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }

        rotateMatrix(matrix);
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    /**
     * layerwise rotation
     * time O(n^2)
     * space O(1)
     * @param matrix
     */
    public static void rotateMatrix(int[][] matrix) {
        int rows = matrix.length - 1;
        for (int layer = 0; layer < matrix.length/2; layer++) {
            for (int i = layer; i < rows - layer; i++) {
                int top = matrix[layer][i];
                int right = matrix[i][rows - layer];
                int bottom = matrix[rows - layer][rows - i];
                int left =  matrix[rows - i][layer];

                matrix[layer][i] = left;
                matrix[i][rows - layer] = top;
                matrix[rows - layer][rows - i] = right;
                matrix[rows - i][layer] = bottom;
            }
        }
    }

    /**
     * transpose and swap the rows
     * time O(n^2)
     * space O(1)
     * @param matrix
     */
    public static void rotateMatrix1(int[][] matrix) {
        int rows = matrix.length;
        // swap rows
        for (int i = 0; i < rows/2; i++) {
            int[] tmp = matrix[i];
            matrix[i] = matrix[rows - 1 - i];
            matrix[rows - 1 - i] = tmp;
        }


        // transpose
        int cols = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = i + 1; j < cols; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }



    }
}
