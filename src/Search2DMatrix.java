/**
 * Search a 2D Matrix
 * Solved
 * You are given an m x n 2-D integer array matrix and an integer target.
 *
 * Each row in matrix is sorted in non-decreasing order.
 * The first integer of every row is greater than the last integer of the previous row.
 * Return true if target exists within matrix or false otherwise.
 *
 * Can you write a solution that runs in O(log(m * n)) time?
 * Example 1
 * Input: matrix = [[1,2,4,8],[10,11,12,13],[14,20,30,40]], target = 10
 *
 * Output: true
 * Example 2
 * Input: matrix = [[1,2,4,8],[10,11,12,13],[14,20,30,40]], target = 15
 *
 * Output: false
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -10000 <= matrix[i][j], target <= 10000
 */
public class Search2DMatrix {
    public static void main(String[] args) {
       int[][] matrix = {{1,2,4,8},{10,11,12,13},{14,20,30,40}};
       int target = 10;
        System.out.println(searchMatrix(matrix, target));
    }

    static boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int left = 0;
        int right = rows - 1;
        while (left <= right) {
            int midRow = left + (right - left)/2;
            if (matrix[midRow][0] > target) {
                right = midRow - 1;
            } else if (matrix[midRow][cols - 1] < target){
                left = midRow + 1;
            } else {
                break;
            }
        }

        if (left > right) {
            return false;
        }
        int midRow = left + (right - left)/2;
        return binarySearch(matrix[midRow], target);
    }

    static boolean binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left)/2;
            if (arr[mid] == target)
                return true;
            if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
}
