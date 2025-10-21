package Graph.util;
import java.util.*;

public class BFS {
    public static List<Integer> run(Graph g, int start, boolean showTree) {
        int n = g.size();
        boolean[] visited = new boolean[n + 1];
        List<Integer> order = new ArrayList<>();
        List<int[]> tree = new ArrayList<>();

        Queue<Integer> q = new ArrayDeque<>();
        for (int s = start; s <= n; s++) {
            if (!visited[s]) {
                visited[s] = true;
                q.add(s);
                while (!q.isEmpty()) {
                    int u = q.poll();
                    order.add(u);
                    for (int v : g.neighbors(u)) {
                        if (!visited[v]) {
                            visited[v] = true;
                            q.add(v);
                            if (showTree) tree.add(new int[]{u, v});
                        }
                    }
                }
            }
        }
        for (int s = 1; s < start; s++) {
            if (!visited[s]) {
                visited[s] = true;
                q.add(s);
                while (!q.isEmpty()) {
                    int u = q.poll();
                    order.add(u);
                    for (int v : g.neighbors(u)) {
                        if (!visited[v]) {
                            visited[v] = true;
                            q.add(v);
                            if (showTree) tree.add(new int[]{u, v});
                        }
                    }
                }
            }
        }

        System.out.print("BFS order:");
        for (int v : order) System.out.print(" " + v);
        System.out.println();

        if (showTree) {
            System.out.println("BFS tree edges (u v):");
            for (int[] e : tree) System.out.println(e[0] + " " + e[1]);
        }
        return order;
    }
}
