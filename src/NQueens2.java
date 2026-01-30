/**
 * 52. N-Queens II
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard
 * such that no two queens attack each other.
 * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 * Example 1:
 * Input: n = 4
 * Output: 2
 * Explanation: There are two distinct solutions to the 4-queens puzzle as shown.
 * Example 2:
 *
 * Input: n = 1
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= n <= 9
 */
public class NQueens2 {
    public static void main(String[] args) {
        int n = 4;
        System.out.println(totalNQueens(n));
    }

    static int totalNQueens(int n) {
        int[] queens = new int[n];
        return helper(0, n, queens);
    }

    static int helper(int row, int n, int[] queens) {
        if (row == n) {
            return 1;
        }

        int count = 0;
        for (int col = 0; col < n; col++) {
            if (isValid(row, col, queens)) {
                queens[row] = col;
                count += helper(row + 1, n, queens);
            }
        }
        return count;
    }

    static boolean isValid(int row, int col, int[] queens) {
        for (int i = 0; i < row; i++) {
            if (queens[i] == col || Math.abs(row - i) == Math.abs(col - queens[i]))
                return false;
        }
        return true;
    }
}

/*
optimized isValid using bitmask
public class NQueensBitmask {
    private int count = 0;

    public int totalNQueens(int n) {
        // (1 << n) - 1 creates a bitmask of 'n' ones.
        // For n=4, this is 1111 in binary.
        int done = (1 << n) - 1;
        solve(0, 0, 0, done);
        return count;
    }

    private void solve(int cols, int ld, int rd, int done) {
        if (cols == done) {
            count++;
            return;
        }

        // Get all available positions in the current row
        // (cols | ld | rd) gives all blocked spots; ~ flips them to 1s (available)
        // AND with 'done' ensures we only care about the first 'n' bits
        int poss = done & (~(cols | ld | rd));

        while (poss != 0) {
            // Get the rightmost set bit (the first available spot)
            int bit = poss & -poss;

            // Subtract this spot from available positions
            poss -= bit;

            // Recurse to next row:
            // ld is shifted left because diagonals move left as we go down
            // rd is shifted right because diagonals move right as we go down
            solve(cols | bit, (ld | bit) << 1, (rd | bit) >> 1, done);
        }
    }
}
 */
