package Graph.util;

import java.util.*;

public class DFS {
    public static List<Integer> run(Graph g, int start, boolean showTree) {
        int n = g.size();
        boolean[] visited = new boolean[n + 1];
        List<Integer> order = new ArrayList<>();
        List<int[]> tree = new ArrayList<>();

        Deque<Integer> stack = new ArrayDeque<>();

        for (int s = start; s <= n; s++) {
            if (!visited[s]) {
                stack.push(s);
                while (!stack.isEmpty()) {
                    int u = stack.pop();
                    if (!visited[u]) {
                        visited[u] = true;
                        order.add(u);
                        for (int v : g.neighbors(u)) {
                            if (!visited[v]) {
                                tree.add(new int[]{u, v});
                                stack.push(v);
                            }
                        }
                    }
                }
            }
        }
        for (int s = 1; s < start; s++) {
            if (!visited[s]) {
                stack.push(s);
                while (!stack.isEmpty()) {
                    int u = stack.pop();
                    if (!visited[u]) {
                        visited[u] = true;
                        order.add(u);
                        for (int v : g.neighbors(u)) {
                            if (!visited[v]) {
                                tree.add(new int[]{u, v});
                                stack.push(v);
                            }
                        }
                    }
                }
            }
        }

        System.out.print("DFS order:");
        for (int v : order) System.out.print(" " + v);
        System.out.println();

        if (showTree) {
            System.out.println("DFS tree edges (u v):");
            for (int[] e : tree) System.out.println(e[0] + " " + e[1]);
        }
        return order;
    }
}
