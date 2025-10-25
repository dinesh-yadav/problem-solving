/**
 * Word Search
 * Given a 2-D grid of characters board and a string word,
 * return true if the word is present in the grid, otherwise return false.
 *
 * For the word to be present it must be possible to
 * form it with a path in the board with horizontally or vertically
 * neighboring cells. The same cell may not be used more than once in a word.
 *
 * Example 1:
 * Input:
 * board = [
 *   ["A","B","C","D"],
 *   ["S","A","A","T"],
 *   ["A","C","A","E"]
 * ],
 * word = "CAT"
 *
 * Output: true
 */
public class WordSearch {
    private static int[][] direction = {{0, 1}, {1,0}, {0, -1}, {-1, 0}};
    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'D'},
                {'S', 'A', 'A', 'T'},
                {'A', 'C', 'A', 'E'},
        };
        String word = "CAT";
        System.out.println(isWordPresent(board, word));
    }

    private static boolean isWordPresent(char[][] board, String word) {
        if (word == null || word.isEmpty())
            return true;
        if (board == null || board.length == 0 || board[0].length == 0)
            return false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (dfsHelper(board, word, 0, i , j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean dfsHelper(char[][] board, String word, int index, int row, int col) {
        if (index == word.length() - 1)
            return true;
        char save = board[row][col];
        board[row][col] = '#';
        int rows = board.length;
        int cols = board[0].length;
        for (int[] d: direction) {
            int nr = row + d[0];
            int nc = col + d[1];
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && board[nr][nc] == word.charAt(index+1)) {
                if (dfsHelper(board, word, index+1, nr, nc)) {
                    board[row][col] = save;
                    return true;
                }
            }
        }
        board[row][col] = save;
        return false;
    }
}
