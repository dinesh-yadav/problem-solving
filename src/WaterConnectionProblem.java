import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * You are given n houses in a colony, numbered from 1 to n, and p pipes connecting these houses. Each house has at most one outgoing pipe and at most one incoming pipe. Your goal is to install tanks and taps efficiently.
 *
 * A tank is installed at a house that has one outgoing pipe but no incoming pipe.
 * A tap is installed at a house that has one incoming pipe but no outgoing pipe.
 * Each pipe is represented as (a[i], b[i], d[i]), where:
 *
 * a[i] is the house where the pipe starts.
 * b[i] is the house where the pipe ends.
 * d[i] is the pipe's diameter.
 * The task is to determine all tank-tap pairs and the minimum pipe diameter in each connection.
 *
 * Examples:
 *
 * Input: n = 9, p = 6, a[] = [7, 5, 4, 2, 9, 3], b[] = [4, 9, 6, 8, 7, 1], d[] = [98, 72, 10, 22, 17, 66]
 * Output: [[2, 8, 22], [3, 1, 66], [5, 6, 10]]
 * Explanation: Identify Tanks and Taps:
 * Tanks (houses with outgoing pipes but no incoming pipes): 2, 3, 5
 * Taps (houses with incoming pipes but no outgoing pipes): 8, 1, 6
 *
 * Find Tank-Tap Paths:
 * 2 -> 8 (Min Diameter = 22) -> [2, 8, 22]
 * 3 -> 1 (Min Diameter = 66) -> [3, 1, 66]
 * 5 -> 9 -> 7 -> 4 -> 6 (Min Diameter = 10) -> [5, 6, 10]
 *
 * Input: n = 4, p = 2, a[] = [1, 3], b[] = [2, 4], d[] = [60, 50]
 * Output: [[1, 2, 60], [3, 4, 50]]
 * Explanation: Identify Tanks and Taps:
 * Tanks (houses with outgoing pipes but no incoming pipes): 1, 3
 * Taps (houses with incoming pipes but no outgoing pipes): 2, 4
 *
 * Find Tank-Tap Paths:
 * 1 -> 2 (Min Diameter = 60) → [1, 2, 60]
 * 3 -> 4 (Min Diameter = 50) → [3, 4, 50]
 */
public class WaterConnectionProblem {
    public static void main(String[] args) {
        int n = 9;
        int p = 6;
        int[] a = {7, 5, 4, 2, 9, 3};
        int[] b = {4, 9, 6, 8, 7, 1};
        int[] d = {98, 72, 10, 22, 17, 66};
        List<List<Integer>> result = waterConnectionDiameter(n, p, a, b, d);
        for (List<Integer> res: result) {
            System.out.println(res);
        }
    }

    static List<List<Integer>> waterConnectionDiameter(int n, int p, int[] src, int[] dest, int[] dia) {
        // create graph
        int[] outgoing = new int[n+1];
        int[] incoming = new int[n+1];
        int[] diameters = new int[n+1];
        Arrays.fill(outgoing, -1);
        Arrays.fill(incoming, -1);
        Arrays.fill(diameters, Integer.MAX_VALUE);

        for (int i = 0; i < p; i++) {
            outgoing[src[i]] = dest[i];
            incoming[dest[i]] = src[i];
            diameters[src[i]] = dia[i];
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {

            // tank -> outgoing but no incoming
            if (outgoing[i] != -1 && incoming[i] == -1) {
                int curr = i;
                int minDia = Integer.MAX_VALUE;
                while(outgoing[curr] != -1) {
                    minDia = Math.min(minDia, diameters[curr]);
                    curr = outgoing[curr];
                }
                result.add(Arrays.asList(i, curr, minDia));
            }
        }
        return result;
    }
}
