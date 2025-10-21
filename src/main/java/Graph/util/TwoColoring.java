package Graph.util;

import java.util.*;

public class TwoColoring {


    public static List<List<Integer>> run(Graph g) {
        int n = g.size();
        int[] color = new int[n + 1];
        Arrays.fill(color, -1);

        boolean isBipartite = true;

        for (int start = 1; start <= n; start++) {
            if (color[start] != -1) continue;
            Queue<Integer> q = new LinkedList<>();
            q.add(start);
            color[start] = 0;

            while (!q.isEmpty()) {
                int u = q.poll();
                for (int v : g.neighbors(u)) {
                    if (color[v] == -1) {
                        color[v] = 1 - color[u];
                        q.add(v);
                    } else if (color[v] == color[u]) {
                        isBipartite = false;
                        break;
                    }
                }
                if (!isBipartite) return null;
            }
        }


        List<Integer> V0 = new ArrayList<>();
        List<Integer> V1 = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (color[i] == 0) V0.add(i);
            else if (color[i] == 1) V1.add(i);
        }

        List<List<Integer>> result = new ArrayList<>();
        result.add(V0);
        result.add(V1);
        return result;
    }
}
