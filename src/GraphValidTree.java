import java.util.*;

/**
 * Graph Valid Tree
 * Solved
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
 *
 * Example 1:
 *
 * Input:
 * n = 5
 * edges = [[0, 1], [0, 2], [0, 3], [1, 4]]
 *
 * Output:
 * true
 * Example 2:
 *
 * Input:
 * n = 5
 * edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]]
 *
 * Output:
 * false
 * Note:
 *
 * You can assume that no duplicate edges will appear in edges.
 * Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 * Constraints:
 *
 * 1 <= n <= 100
 * 0 <= edges.length <= n * (n - 1) / 2
 */
public class GraphValidTree {
    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        //int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};
        System.out.println(isGraphValidTreeBfs(n, edges));
        System.out.println(isGraphValidTreeDfs(n, edges));
    }

    static boolean isGraphValidTreeBfs(int n, int[][] edges) {
        if (n == 0) {
            return true;
        }
        if (edges.length > n - 1) {
            return false;
        }

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge: edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        Set<Integer> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, -1});
        visited.add(0);

        while(!queue.isEmpty()) {
            int[] element = queue.poll();
            int curr = element[0];
            int prev = element[1];
            for (int neigh: adj.get(curr)) {
                if (neigh == prev) {
                    continue;
                }

                if (visited.contains(neigh)) {
                    return false;
                }

                visited.add(neigh);
                queue.add(new int[]{neigh, curr});
            }
        }
        return visited.size() == n;
    }

    static boolean isGraphValidTreeDfs(int n, int[][] edges) {
        if (n == 0) {
            return true;
        }
        if (edges.length > n - 1) {
            return false;
        }

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge: edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        Set<Integer> visited = new HashSet<>();
        return dfs(0, -1, adj, visited) && visited.size() == n;
    }

    static boolean dfs(int curr, int prev, List<List<Integer>> adj, Set<Integer> visited) {
        if(visited.contains(curr)) {
            return false;
        }
        visited.add(curr);

        for (int neigh: adj.get(curr)) {
            if (neigh == prev) {
                continue;
            }
            if (!dfs(neigh, curr, adj, visited)) {
                return false;
            }
        }
        return true;
    }
}
