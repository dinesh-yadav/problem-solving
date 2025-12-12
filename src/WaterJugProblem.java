/**
 * The Water Jug Problem - Count Min Steps
 * Last Updated : 23 Jul, 2025
 * You are given a m liter jug and a n liter jug where 0 < m < n. Both the jugs are initially empty. The jugs don’t have markings to allow measuring smaller quantities. You have to use the jugs to measure d liters of water where d < n. Determine the minimum no of operations to be performed to obtain d liters of water in one of the two jugs.
 * The operations you can perform are:
 *
 * Empty a Jug
 * Fill a Jug (You may assume that you have unlimited supply of water)
 * Pour water from one jug to the other until one of the jugs is either empty or full.
 * Input: m = 2, n = 3, d = 1
 * Output: 2
 * Explanation:
 * 1. Fill up the 3 litre jug
 * 2. Pour 2 litre of water from the 3  litre Jug to 2 litre Jug, Now the 3 litre jug has 1 litre water.
 *
 * Input: m = 3, n = 5, d = 4
 * Output: 6
 * Explanation:
 * 1. Fill up the 5 litre jug.
 * 2. Then fill up the 3 litre jug using 5 litre   jug. Now 5 litre jug contains 2 litre water.
 * 3. Empty the 3 litre jug.
 * 4. Now pour the 2 litre of water from 5 litre    jug to 3 litre jug.
 * 5. Now 3 litre jug contains 2 litre of water and 5 litre jug is empty. Now fill up the 5 litre jug.
 * 6. Now fill one litre of water from 5 litre jug to 3 litre jug. Now we have 4 litre water in 5 litre jug
 *
 * Input: m = 2, n = 3, d = 5
 * Output: -1
 * Explanation: We need to fill in one jug only
 *
 * Input: m = 2, n = 4, d = 3
 * Output: -1
 * Explanation: Not possible
 */
public class WaterJugProblem {
    public static void main(String[] args) {
        System.out.println(minSteps(2, 3, 5));
        System.out.println(minSteps(2, 4, 3));
        System.out.println(minSteps(3, 5, 4));
        System.out.println(minSteps(2, 3, 1));
    }

    static int minSteps(int m, int n, int d) {
        if (m > n) {
            int tmp = m;
            m = n;
            n = tmp;
        }

        if (d > n) {
            return -1;
        }

        if (d % gcd(m, n) != 0) {
            return -1;
        }

        return Math.min(pour(m, n, d), pour(n, m, d));
    }

    static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static int pour(int fromCap, int toCap, int d) {
        int from = fromCap;
        int to = 0;
        int steps = 1;
        while(from != d && to != d) {
            int p = Math.min(from, toCap - to);

            from -= p;
            to += p;
            steps++;

            if (from == d || to == d) {
                return steps;
            }

            if (from == 0) {
                from = fromCap;
                steps++;
            }

            if (to == toCap) {
                to = 0;
                steps++;
            }
        }
        return steps;
    }
}
