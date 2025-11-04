import java.util.Arrays;

/**
 * Paint a Matrix
 * Given a 2-color matrix and a point start, flip all points in the adjacent region of start to the opposite color.
 *
 * Example
 *
 * Input:
 * image = [
 *   [0, 0, 1, 0],
 *   [0, 0, 1, 0],
 *   [0, 0, 1, 0],
 *   [0, 0, 1, 0]
 * ]
 * row = 0
 * col = 1
 * newColor = 1
 *
 * Output:
 * [
 *   [1, 1, 1, 0],
 *   [1, 1, 1, 0],
 *   [1, 1, 1, 0],
 *   [1, 1, 1, 0],
 * ]
 * Explanation: (0, 1) is color 0, the whole adjacent region was flipped to 1's
 * Constraints
 *
 * Cells can only be 1 or 0
 */
public class PaintMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}
        };
        int row = 1;
        int col = 0;
        paintMatrix(matrix, row, col);
        for (int[] rw: matrix) {
            System.out.println(Arrays.toString(rw));
        }
    }

    static int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public static void paintMatrix(int[][] matrix, int row, int col) {
        int origCol = matrix[row][col];
        int newCol = 1 - origCol;
        matrix[row][col] = newCol;
        int rows = matrix.length;
        int cols = matrix[0].length;
        for(int[] d: direction) {
            int nr = row +  d[0];
            int nc = col + d[1];
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols
                && matrix[nr][nc] == origCol) {
                paintMatrix(matrix, nr, nc);
            }
        }

    }
}
