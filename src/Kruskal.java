import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Kruskal {
    public static void main(String[] args) {
        int[] wells = {1, 2, 2};
        int[][] pipes = {{1, 2, 1}, {2, 3, 1}};
        System.out.println(optimizePipeline(3, wells, pipes));
    }
    public static int optimizePipeline(int n, int[] wells, int[][] pipes) {
        List<int[]> edges = new ArrayList<>();
        for (int i = 1; i <= wells.length; i++) {
            edges.add(new int[]{wells[i - 1], 0, i});
        }

        for (int[] pipe: pipes) {
            edges.add(new int[]{pipe[2], pipe[0], pipe[1]});
        }

        edges.sort(Comparator.comparingInt(a -> a[0]));

        UnionFind unionFind = new UnionFind(n + 1);
        int totalCost = 0;
        for(int[] edge: edges) {
            int cost = edge[0];
            int u = edge[1];
            int v = edge[2];

            if (unionFind.union(u, v)) {
                totalCost += cost;
            }
        }

        return totalCost;
    }
}

class UnionFind {
    int[] parent;
    int[] rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public boolean union(int x, int y) {
        int rootx = find(x);
        int rooty = find(y);
        if (rootx == rooty)
            return false;

        if (rank[rootx] < rank[rooty]) {
            parent[rootx] = rooty;
        } else if (rank[rooty] < rank[rootx]) {
            parent[rooty] = rootx;
        } else {
            parent[rootx] = rooty;
            rank[rooty]++;
        }
        return true;
    }
}


