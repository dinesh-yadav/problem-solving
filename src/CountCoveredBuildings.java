import java.util.Arrays;

/**
 * 3531. Count Covered Buildings
 * You are given a positive integer n, representing an n x n city. You are also given a 2D grid buildings, where buildings[i] = [x, y] denotes a unique building located at coordinates [x, y].
 *
 * A building is covered if there is at least one building in all four directions: left, right, above, and below.
 *
 * Return the number of covered buildings.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: n = 3, buildings = [[1,2],[2,2],[3,2],[2,1],[2,3]]
 *
 * Output: 1
 *
 * Explanation:
 *
 * Only building [2,2] is covered as it has at least one building:
 * above ([1,2])
 * below ([3,2])
 * left ([2,1])
 * right ([2,3])
 * Thus, the count of covered buildings is 1.
 * Example 2:
 *
 *
 *
 * Input: n = 3, buildings = [[1,1],[1,2],[2,1],[2,2]]
 *
 * Output: 0
 *
 * Explanation:
 *
 * No building has at least one building in all four directions.
 */
public class CountCoveredBuildings {
    public static void main(String[] args) {
        int n1 = 4;
        int[][] buildings1 = {{1,2},{2,2},{4,2},{2,1},{2,3}};
        System.out.println(countCoveredBuildings(n1, buildings1)); // Output: 1

        int n2 = 3;
        int[][] buildings2 = {{1,1},{1,2},{2,1},{2,2}};
        System.out.println(countCoveredBuildings(n2, buildings2)); // Output: 0


        System.out.println(countCoveredBuildingsOptimized(n1, buildings1)); // Output: 1

        System.out.println(countCoveredBuildingsOptimized(n2, buildings2)); // Output: 0
    }

    // time complexity: O(m * n) where m is the number of buildings and n is the size of the grid
    // space complexity: O(n^2) for the grid
    public static int countCoveredBuildings(int n, int[][] buildings) {
        boolean[][] grid = new boolean[n + 1][n + 1];
        for (int[] building : buildings) {
            grid[building[0]][building[1]] = true;
        }

        int coveredCount = 0;
        for (int[] building : buildings) {
            int x = building[0];
            int y = building[1];

            if (hasBuildingAbove(grid, x, y) &&
                hasBuildingBelow(grid, x, y, n) &&
                hasBuildingLeft(grid, x, y) &&
                hasBuildingRight(grid, x, y, n)) {
                coveredCount++;
            }
        }

        return coveredCount;
    }

    private static boolean hasBuildingAbove(boolean[][] grid, int x, int y) {
        for (int i = x - 1; i >= 1; i--) {
            if (grid[i][y]) {
                return true;
            }
        }
        return false;
    }
    private static boolean hasBuildingBelow(boolean[][] grid, int x, int y, int n) {
        for (int i = x + 1; i <= n; i++) {
            if (grid[i][y]) {
                return true;
            }
        }
        return false;
    }
    private static boolean hasBuildingLeft(boolean[][] grid, int x, int y) {
        for (int j = y - 1; j >= 1; j--) {
            if (grid[x][j]) {
                return true;
            }
        }
        return false;
    }
    private static boolean hasBuildingRight(boolean[][] grid, int x, int y, int n) {
        for (int j = y + 1; j <= n; j++) {
            if (grid[x][j]) {
                return true;
            }
        }
        return false;
    }

    public static int countCoveredBuildingsOptimized(int n, int[][] buildings) {
        int[] rowMax = new int[n + 1];
        int[] rowMin = new int[n + 1];
        int[] colMax = new int[n + 1];
        int[] colMin = new int[n + 1];
        Arrays.fill(rowMin, n + 1);
        Arrays.fill(colMin, n + 1);

        for (int[] building: buildings) {
            int x = building[0];
            int y = building[1];

            rowMax[y] = Math.max(rowMax[y], x);
            rowMin[y] = Math.min(rowMin[y], x);

            colMax[x] = Math.max(colMax[x], y);
            colMin[x] = Math.min(colMin[x], y);
        }

        int count = 0;
        for (int[] building: buildings) {
            int x = building[0];
            int y = building[1];
            if (x < rowMax[y] && x > rowMin[y] && y < colMax[x] && y > colMin[x]) {
                count++;
            }
        }
        return count;
    }
}
