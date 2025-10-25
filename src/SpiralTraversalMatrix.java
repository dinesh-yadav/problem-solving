import java.util.List;
import java.util.ArrayList;
/**
 * Spiral Traversal of a Matrix
 * Given an mxn matrix, return a spiral readout of its values going clockwise
 * & moving inward layer by layer (starting from the top-left).
 *
 * Input-Output
 *
 * Example 1
 *
 * Input:
 * [
 *  [1, 2, 3],
 *  [4, 5, 6],
 *  [7, 8, 9]
 * ]
 * Output: [1, 2, 3, 6, 9, 8, 7, 4, 5]
 */
public class SpiralTraversalMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };

        System.out.println(spiralTraversal(matrix));
    }

    public static List<Integer> spiralTraversal(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return result;
        }

        int top = 0;
        int left = 0;
        int bottom = matrix.length - 1;
        int right = matrix[0].length - 1;
        while (top <= bottom && left <= right){
            for (int col = left; col <= right; col++) {
                result.add(matrix[top][col]);
            }
            top++;

            for(int row = top; row <= bottom; row++) {
                result.add(matrix[row][right]);
            }
            right--;

            if (left <= right) {
                for (int col = right; col >= left; col--) {
                    result.add(matrix[bottom][col]);
                }
            }
            bottom--;

            if (top <= bottom) {
                for(int row = bottom; row >= top; row--) {
                    result.add(matrix[row][left]);
                }
            }
            left++;
        }

        return result;
    }
}
