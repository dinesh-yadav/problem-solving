/**
 * 96. Unique Binary Search Trees
 * Given an integer n, return the number of structurally unique BST's (binary search trees)
 * which has exactly n nodes of unique values from 1 to n.
 * Example 1:
 * Input: n = 3
 * Output: 5
 * Example 2:
 * Input: n = 1
 * Output: 1
 * Constraints:
 * 1 <= n <= 19
 */
public class UniqueBsts {
    public static void main(String[] args) {
        System.out.println(numberOfUniqueBsts(3));
        System.out.println(numberOfUniqueBsts(5));
        System.out.println(numberOfUniqueBstsCatalanNumber1(5));
        System.out.println(numberOfUniqueBstsCatalanNumber2(5));

    }

    static int numberOfUniqueBsts(int n) {
        if (n == 0 || n == 1)
            return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i + 1; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    static int numberOfUniqueBstsCatalanNumber1(int n) {
        // https://www.geeksforgeeks.org/dsa/program-nth-catalan-number/
        // Cn = (1/(n+1)) (2n n)
        // Cn = (2n)!/(n! (n+1)!)
        return (int)ncr(2*n, n)/(n + 1);
    }

    static long ncr(int n, int r) {
        long ans = 1;
        for(int i = 0; i < r; i++) {
            ans *= n-i;
            ans /= i+1;
        }
        return ans;
    }

    static int numberOfUniqueBstsCatalanNumber2(int n) {
        // C0 = 1, C1 = 1
        // Cn+1 = ((4*n + 2)/(n + 2)) * Cn
        double ans = 1;
        for(int i = 0; i < n; i++)
            ans *= 1.0 * (4 * i + 2) / (i + 2);
        return (int)ans;
    }
}
