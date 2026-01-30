import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 51. N-Queens
 * Hard
 * Topics
 * premium lock icon
 * Companies
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 4
 * Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
 * Example 2:
 *
 * Input: n = 1
 * Output: [["Q"]]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 9
 */
import java.util.*;

public class NQueens1 {
    public static void main(String[] args) {
        int n = 4;
        List<List<String>> solutions = solveNQueens(n);
        for (List<String> sol : solutions) {
            for (String row : sol) System.out.println(row);
            System.out.println();
        }
    }

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        // queens[i] = j means queen at row i is at column j
        int[] queens = new int[n];
        helper(0, n, queens, res);
        return res;
    }

    private static void helper(int row, int n, int[] queens, List<List<String>> res) {
        if (row == n) {
            res.add(construct(queens, n));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isValid(row, col, queens)) {
                queens[row] = col;
                helper(row + 1, n, queens, res);
                // No explicit "remove" needed because we just overwrite queens[row]
            }
        }
    }

    private static boolean isValid(int row, int col, int[] queens) {
        for (int i = 0; i < row; i++) {
            int prevCol = queens[i];
            // 1. Same Column check
            // 2. Diagonal check: if |row1 - row2| == |col1 - col2|, they are diagonal
            if (prevCol == col || Math.abs(row - i) == Math.abs(col - prevCol)) {
                return false;
            }
        }
        return true;
    }

    private static List<String> construct(int[] queens, int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }
}
/* optimized for isValid method
//Checking columns is straightforward, but diagonals require a little trick:
//Columns: Use a boolean array cols[n].
//Main Diagonals (top-left to bottom-right):
//    Along these diagonals, the value of $(row - col)$ is constant.
//    Since this can be a negative number, we add an offset of $(n - 1)$ to keep the index positive.
//Anti-Diagonals (top-right to bottom-left):
//    Along these diagonals, the value of $(row + col)$ is constant.
import java.util.*;

public class NQueensOptimized {
    private static boolean[] cols;
    private static boolean[] diag1; // (row - col)
    private static boolean[] diag2; // (row + col)

    public static List<List<String>> solveNQueens(int n) {
        cols = new boolean[n];
        diag1 = new boolean[2 * n];
        diag2 = new boolean[2 * n];

        List<List<String>> res = new ArrayList<>();
        int[] queens = new int[n];
        helper(0, n, queens, res);
        return res;
    }

    private static void helper(int row, int n, int[] queens, List<List<String>> res) {
        if (row == n) {
            res.add(construct(queens, n));
            return;
        }

        for (int col = 0; col < n; col++) {
            // Constant time O(1) checks!
            int d1 = row - col + n;
            int d2 = row + col;

            if (!cols[col] && !diag1[d1] && !diag2[d2]) {
                // Place queen
                queens[row] = col;
                cols[col] = diag1[d1] = diag2[d2] = true;

                helper(row + 1, n, queens, res);

                // Backtrack (reset)
                cols[col] = diag1[d1] = diag2[d2] = false;
            }
        }
    }

    private static List<String> construct(int[] queens, int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }
}
 */