import java.util.*;

class WaterPipelineOptimization {
    static class UnionFind {
        int[] parent, rank;

        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        boolean union(int a, int b) {
            int pa = find(a);
            int pb = find(b);

            if (pa == pb) return false;

            if (rank[pa] < rank[pb]) {
                parent[pa] = pb;
            } else if (rank[pb] < rank[pa]) {
                parent[pb] = pa;
            } else {
                parent[pb] = pa;
                rank[pa]++;
            }
            return true;
        }
    }

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        List<int[]> edges = new ArrayList<>();

        // Add well edges: (cost, 0, i)
        for (int i = 1; i <= n; i++) {
            edges.add(new int[]{wells[i - 1], 0, i});
        }

        // Add pipe edges: (cost, u, v)
        for (int[] p : pipes) {
            edges.add(new int[]{p[2], p[0], p[1]});
        }

        // Sort edges by cost
        Collections.sort(edges, (a, b) -> a[0] - b[0]);

        UnionFind uf = new UnionFind(n + 1);
        int totalCost = 0;

        for (int[] e : edges) {
            int cost = e[0];
            int u = e[1];
            int v = e[2];

            if (uf.union(u, v)) {
                totalCost += cost;
            }
        }

        return totalCost;
    }

    public static void main(String[] args) {
        WaterPipelineOptimization waterPipelineOptimization = new WaterPipelineOptimization();
        int n = 3;
        int[] wells = {1, 2, 3};
        int[][] pipes = {{1, 2, 1}, {2, 3, 1}};
        System.out.println(waterPipelineOptimization.minCostToSupplyWater(n, wells, pipes));
    }
}

