package Graph.util;
import java.util.*;

public class TopologicalSort {
    public static List<Integer> run(Graph g) {
        int n = g.size();
        int[] indegree = new int[n + 1];
        List<Integer> order = new ArrayList<>();
        Queue<Integer> vertex = new ArrayDeque<>();

        for (int u = 1; u <= n; u++) {
            for (int v : g.adj.get(u)) {
                indegree[v]++;
            }
        }

        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) vertex.add(i);
        }

        int count = 0;
        while (!vertex.isEmpty()) {
            int curr_ver = vertex.poll();
            order.add(curr_ver);
            count++;

            for (int v : g.adj.get(curr_ver)) {
                indegree[v]--;
                if (indegree[v] == 0) vertex.add(v);
            }
        }

        if (count < n) {
            System.out.println("Graf zawiera cykl.");
            return Collections.emptyList();
        } else {
            System.out.println("Graf jest acykliczny.");
            if(g.size() <= 200) {
                System.out.println ("Kolejność topologiczna:");
            System.out.println(order);
            }
            return order;
        }
    }
}
