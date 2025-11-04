import java.util.Arrays;

/**
 * Compute Enclosed Regions
 *
 * Given a matrix with only characters 'X' and 'O', return a matrix with all enclosed 'O's marked to an 'X'.
 *
 * A region of 'O's is "enclosed" if the region does not reach a 'O' that touches any edge of the matrix. All 'O's on the edge of the matrix are safe from being marked to an 'X'.
 *
 * Input-Output
 *
 * Example 1
 *
 * Input: [
 *   ['X', 'X', 'O', 'X', 'X'],
 *   ['X', 'X', 'O', 'X', 'X'],
 *   ['X', 'X', 'O', 'X', 'X'],
 *   ['X', 'X', 'X', 'O', 'X'],
 *   ['X', 'X', 'O', 'X', 'X'],
 *   ['X', 'X', 'O', 'X', 'X']
 * ]
 *
 * Output:
 * [
 *   ['X', 'X', 'O', 'X', 'X'],
 *   ['X', 'X', 'O', 'X', 'X'],
 *   ['X', 'X', 'O', 'X', 'X'],
 *   ['X', 'X', 'X', 'X', 'X'],
 *   ['X', 'X', 'O', 'X', 'X'],
 *   ['X', 'X', 'O', 'X', 'X']
 * ]
 *
 * Explanation: The two regions touching the upper and lower edge of the matrix are safe.
 * The stray 'O' in row index 3 is not touching an 'O' connected to
 * any edge to make it safe (it can only reach up, down, left, & right).
 * Constraints
 *
 * The matrix will be m x n
 * 2 <= m <= n <= 100
 */
public class ComputeEnclosedRegions {
    public static void main(String[] args) {
        char[][] matrix = {
                {'X', 'X', 'O', 'X', 'X'},
                {'X', 'X', 'O', 'X', 'X'},
                {'X', 'X', 'O', 'X', 'X'},
                {'X', 'X', 'X', 'O', 'O'},
                {'X', 'O', 'O', 'X', 'X'},
                {'X', 'X', 'O', 'X', 'X'}
        };

        updateEnclosedRegions(matrix);
        for (char[] arr: matrix) {
            System.out.println(Arrays.toString(arr));
        }
    }

    static void updateEnclosedRegions(char[][] matrix) {
        if (matrix == null || matrix.length < 3 || matrix[0].length < 3) {
            return;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        // top and bottom row
        for (int i = 0; i < cols; i++) {
            if (matrix[0][i] == 'O')
                dfs(matrix, 0, i);
            if (matrix[rows - 1][i] == 'O')
                dfs(matrix, rows - 1, i);
        }

        // left and right columns
        for (int i = 1; i < rows - 1; i++) {
            if (matrix[i][0] == 'O')
                dfs(matrix, i, 0);
            if (matrix[i][cols - 1] == 'O')
                dfs(matrix, i, cols - 1);
        }

        // mark 'O' to '#' and '#' to 'O'
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 'O')
                    matrix[i][j] = 'X';
                else if (matrix[i][j] == '#')
                    matrix[i][j] = 'O';
            }
        }
    }

    static int[][] direction = {{0,1}, {1, 0}, {0, -1}, {-1, 0}};
    static void dfs(char[][] matrix, int row, int col) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        if (row < 0 || col < 0 || row >= rows || col >= cols || matrix[row][col] != 'O') {
            return;
        }
        matrix[row][col] = '#';
        for (int[] d: direction) {
            int nr = row + d[0];
            int nc = col + d[1];
            dfs(matrix, nr, nc);
        }
    }
}
