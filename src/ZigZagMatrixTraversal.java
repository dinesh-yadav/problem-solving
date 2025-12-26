import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
/**
 * Given a matrix of n x m integers, where n represents the number of rows and m represents the number of columns. Both n and m range from 1 to 100, inclusive.
 *
 * The matrix cells may contain either a positive, a negative integer, or zero, with values ranging from -100 to 100, inclusive.
 *
 * In this task, you are required to traverse the matrix diagonally from the top-left cell to the bottom-right cell in a zigzag pattern. Start from the top-left cell, move one cell to the right (if it exists), then move one step diagonally down-left. After reaching a left (bottom) boundary, move one step down (right) and start moving diagonally up-right. Continue this pattern until you reach the last cell of the matrix. Your task is to return a list of tuples, each tuple containing the index pair (in 0-based indexing format) of cells with negative integers encountered during your traversal.
 *
 * For example, consider a 3 x 4 matrix:
 *
 * Copy to clipboard
 * {{1, -2, 3, -4},
 * {5, -6, 7, 8},
 * {-9, 10, -11, 12}}
 * The traversal in a zigzag pattern will result in: {1, -2, 5, -9, -6, 3, -4, 7, 10, -11, 8, 12}.
 *
 * The negative integers in this sequence and their corresponding positions in the matrix are: {-2, -9, -6, -4, -11}, with indices: {{0, 1}, {2, 0}, {1, 1}, {0, 3}, {2, 2}}.
 *
 * Your function, solution(matrix), should then return these indices as a list of arrays: {{0, 1}, {2, 0}, {1, 1}, {0, 3}, {2, 2}}.
 */
public class ZigZagMatrixTraversal {
    public static void main(String[] args) {
        int[][] matrix = {{1, -2, 3, -4},
                            {5, -6, 7, 8},
                        {-9, 10, -11, 12}};
        List<int[]> output = zigZag(matrix);
        for (int[] r: output) {
            System.out.print(Arrays.toString(r) + " ");
        }
    }
    static List<int[]> zigZag(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        List<int[]> result = new ArrayList<>();
        int row = 0;
        int col = 0;
        int dir = 1;
        for (int i = 0; i < rows*cols; i++) {
            if (matrix[row][col] < 0) {
                result.add(new int[]{row, col});
            }
            if (dir == 1) {
                if (col == cols - 1) {
                    row++;
                    dir = -1;
                } else if (row == 0) {
                    col++;
                    dir = -1;
                } else  {
                    row--; col++;
                }
            } else {
                if (row == rows - 1) {
                    col++;
                    dir = 1;
                } else if (col == 0) {
                    row++;
                    dir = 1;
                } else {
                    row++; col--;
                }
            }
        }
        return result;
    }
}
