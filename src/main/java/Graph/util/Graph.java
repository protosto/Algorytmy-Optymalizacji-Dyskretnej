package Graph.util;
import java.util.*;

public class Graph {
    private final int n;
    private final boolean directed;
    final List<List<Integer>> adj;

    public Graph(int n, boolean directed) {
        this.n = n;
        this.directed = directed;
        adj = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
    }

    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        if (!directed) adj.get(v).add(u);
    }

    public List<Integer> neighbors(int u) {
        return adj.get(u);
    }

    public int size() { return n; }

    public boolean isDirected() { return directed; }

    public static Graph readFromStdin(Scanner sc) {
        String type = sc.next().trim();
        boolean directed = type.equalsIgnoreCase("D");
        int n = sc.nextInt();
        int m = sc.nextInt();
        Graph g = new Graph(n, directed);
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(), v = sc.nextInt();
            g.addEdge(u, v);
        }
        return g;
    }
}
