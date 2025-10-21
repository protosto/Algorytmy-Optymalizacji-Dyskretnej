package Graph.util;
import java.util.*;

public class StronglyConnectedComponents {

    public static List<List<Integer>> run(Graph g) {
        int n = g.size();
        boolean[] visited = new boolean[n + 1];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) dfs1_iterative(g, i, visited, stack);
        }

        Graph gt = transpose(g);

        Arrays.fill(visited, false);
        List<List<Integer>> components = new ArrayList<>();

        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (!visited[v]) {
                List<Integer> comp = dfs2_iterative(gt, v, visited);
                components.add(comp);
            }
        }

        return components;
    }

    private static void dfs1_iterative(Graph g, int start, boolean[] visited, Deque<Integer> stack) {
        Deque<Integer> local = new ArrayDeque<>();
        local.push(start);
        while (!local.isEmpty()) {
            int v = local.pop();
            if (v > 0) {
                if (!visited[v]) {
                    visited[v] = true;
                    local.push(-v);
                    for (int u : g.neighbors(v)) {
                        if (!visited[u]) local.push(u);
                    }
                }
            } else {
                stack.push(-v);
            }
        }
    }

    private static List<Integer> dfs2_iterative(Graph g, int start, boolean[] visited) {
        List<Integer> comp = new ArrayList<>();
        Deque<Integer> s = new ArrayDeque<>();
        s.push(start);
        visited[start] = true;

        while (!s.isEmpty()) {
            int v = s.pop();
            comp.add(v);
            for (int u : g.neighbors(v)) {
                if (!visited[u]) {
                    visited[u] = true;
                    s.push(u);
                }
            }
        }
        return comp;
    }

    private static Graph transpose(Graph g) {
        Graph gt = new Graph(g.size(), g.isDirected());
        for (int u = 1; u <= g.size(); u++) {
            for (int v : g.neighbors(u)) {
                gt.addEdge(v, u);
            }
        }
        return gt;
    }
}
