import java.util.*;

/**
 * Shortest Reach from a given source using bfs
 *
 */
public class ShortestReach {

    /*
     * Complete the 'bfs' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER m
     *  3. 2D_INTEGER_ARRAY edges
     *  4. INTEGER s
     */

    public static List<Integer> bfs(int n, List<List<Integer>> edges, int s) {
        // Write your code here
        Integer[] result = new Integer[n];
        Arrays.fill(result, -1);
        result[s - 1] = 0;

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (List<Integer> edge: edges) {
            adj.get(edge.get(0) - 1).add(edge.get(1) - 1);
            adj.get(edge.get(1) - 1).add(edge.get(0) - 1);
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(s - 1);


        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int v = q.poll();
                int add = result[v];
                for (int nei : adj.get(v)) {
                    if (result[nei] == -1) {
                        result[nei] = add + 6;
                        q.offer(nei);
                    }
                }

            }
        }
        List<Integer> res = new ArrayList<>();
        for (int num: result) {
            if (num == 0)
                continue;
            res.add(num);
        }
        return res;

    }

    public static void main(String[] args) {
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            edges.add(new ArrayList<>());
        }
        edges.get(0).add(1);
        edges.get(0).add(2);
        edges.get(1).add(1);
        edges.get(1).add(3);

        System.out.println(bfs(4, edges, 1));
    }
}

