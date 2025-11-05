import java.util.Arrays;

/**
 * Given two strings s1 and s2, return the length of the longest common subsequence
 * of characters between the two strings.
 *
 * Input-Output
 *
 * Example 1
 *
 * Input:
 * s1 = "ABCD"
 * s2 = "ABCD"
 *
 * Output: 4
 *
 * Explanation:
 * "**ABCD**"
 * "**ABCD**"
 * Both strings share the subsequence "A", "B", "C", "D".
 * Example 2
 *
 * Input:
 * s1 = "ADC"
 * s2 = "ABCD"
 *
 * Output: 2
 *
 * Explanation:
 * "**AD**C"
 * "**A**BC**D**"
 * Both strings share the subsequence "A", "D".
 * Example 3
 *
 * Input:
 * s1 = "DBC"
 * s2 = "CBD"
 *
 * Output: 1
 *
 * Explanation:
 * "**D**BC"
 * "CB**D**"
 * "D**B**C"
 * "C**B**D"
 * "DB**C**"
 * "**C**BD"
 */
public class LongestCommonSubSequence {
    public static void main(String[] args) {
        String s1 = "ABCD";
        String s2 = "ABDC";

        System.out.println(longestCommonSubsequenceCache(s1, s2));

        System.out.println(longestCommonSubsequence(s1, s2));

        System.out.println(longestCommonSubsequenceWithMemoization(s1, s2));
    }

    // O(m*n) time
    public static int longestCommonSubsequenceCache(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] cache = new int[m + 1][n + 1];

        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i == 0 || j == 0) {
                    cache[i][j] = 0;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    cache[i][j] = cache[i - 1][j - 1] + 1;
                } else {
                    cache[i][j] = Math.max(cache[i - 1][j], cache[i][j - 1]);
                }
            }
        }
        return cache[m][n];

    }

    // O(2^(m*n))
    public static int longestCommonSubsequence(String s1, String s2) {
        if(s1.isEmpty() || s2.isEmpty())
            return 0;
        int m = s1.length();
        int n = s2.length();

        String s1WithoutLastElement = s1.substring(0, m - 1);
        String s2WithoutLastElement = s2.substring(0, n - 1);
        char s1LastElement = s1.charAt(m - 1);
        char s2LastElement = s2.charAt(n - 1);
        if (s1LastElement == s2LastElement) {
            return 1 + longestCommonSubsequence(s1WithoutLastElement, s2WithoutLastElement);
        } else {
            return Math.max(longestCommonSubsequence(s1, s2WithoutLastElement),
                    longestCommonSubsequence(s1WithoutLastElement, s2));
        }
    }

    public static int longestCommonSubsequenceWithMemoization(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] mem = new int[m + 1][n + 1];
        for(int[] mr: mem) {
            Arrays.fill(mr, -1);
        }
        return memoizationHelper(s1, s2, mem);
    }

    // O(m*n)
    public static int memoizationHelper(String s1, String s2, int[][] mem) {
        int m = s1.length();
        int n = s2.length();
        if(m == 0 || n == 0) {
            mem[m][n] = 0;
            return mem[m][n];
        }

        if(mem[m][n] != -1) {
            return mem[m][n];
        }

        String s1WithoutLastElement = s1.substring(0, m - 1);
        String s2WithoutLastElement = s2.substring(0, n - 1);
        char s1LastElement = s1.charAt(m - 1);
        char s2LastElement = s2.charAt(n - 1);
        if (s1LastElement == s2LastElement) {
            mem[m][n] = 1 + longestCommonSubsequence(s1WithoutLastElement, s2WithoutLastElement);
        } else {
            mem[m][n] = Math.max(longestCommonSubsequence(s1, s2WithoutLastElement),
                    longestCommonSubsequence(s1WithoutLastElement, s2));
        }
        return mem[m][n];
    }
}
