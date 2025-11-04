import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a graph return true if it is bipartite, return false otherwise.
 *
 * Input-Output
 *
 * Example 1
 *
 * Input:
 * [
 *   [3],
 *   [3],
 *   [4],
 *   [],
 *   [],
 * ]
 *
 * Output: true
 * Explanation: This graph can be bipartitioned such that there are no internal connections within the bipartition.
 * 3  <- 1    2 -> 4
 * ^
 * |
 * 0
 */
public class BipartiteGraph {
    public static void main(String[] args) {
        int[][] graph = {
                {3}, {3}, {4}, {}, {}
        };
        System.out.println(isGraphBipartiteBfs(graph));
        System.out.println(isGraphBipartiteDfs(graph));
    }

    static boolean isGraphBipartiteBfs(int[][] graph) {
        int vertices = graph.length;
        // 0: uncolored, 1 and -1  other two colors
        // divide the graph in two disjoint sets (u, v)
        int[] color = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            if (color[i] == 0 && !bfsHelper(i, graph, color))
                return false;
        }
        return true;
    }

    static boolean bfsHelper(int u, int[][] graph, int[] color) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(u);
        color[u] = 1;
        while(!queue.isEmpty()) {
            int[] v = graph[queue.poll()];
            for (int i : v) {
                if (color[i] == 0) {
                    color[i] = -color[u];
                    queue.add(i);
                } else if(color[i] == color[u]){
                    return false;
                }
            }
        }
        return true;
    }

    static boolean isGraphBipartiteDfs(int[][] graph) {
        int vertices = graph.length;
        // 0: uncolored, 1 and -1  other two colors
        // divide the graph in two disjoint sets (u, v)
        int[] color = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            if (color[i] == 0 && !dfsHelper(i, 1, graph, color))
                return false;
        }
        return true;
    }

    static boolean dfsHelper(int u, int c, int[][] graph, int[] color) {
        color[u] = c;
        for (int v: graph[u]) {
            if (color[v] == 0) {
                if (!dfsHelper(v, -c, graph, color)) {
                    return false;
                }
            } else if (color[u] == color[v]) {
                return false;
            }
        }
        return true;
    }
}
