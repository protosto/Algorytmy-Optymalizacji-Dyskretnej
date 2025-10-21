package Zadania;

import Graph.util.*;
import java.io.*;
import java.util.*;

public class zad1 {

    private static final String[] TEST_FILES = {
            "src/main/resources/AOD_Test1/1/graph1",
            "src/main/resources/AOD_Test1/1/graph2",
            "src/main/resources/AOD_Test1/1/graph3",
            "src/main/resources/AOD_Test1/1/myGraph"
    };

    public static void main(String[] args) {
        boolean showTree = true;
        int start = 1;

        String outputFile = "results1.txt";

        try (PrintWriter out = new PrintWriter(new FileWriter(outputFile))) {

            for (String path : TEST_FILES) {
                File file = new File(path);
                if (!file.exists()) {
                    System.err.println("Nie znaleziono pliku: " + path);
                    continue;
                }

                out.println("===== " + file.getName() + " =====");
                System.out.println("Przetwarzanie pliku: " + file.getName());

                try (Scanner sc = new Scanner(file)) {
                    Graph g = Graph.readFromStdin(sc);

                    long t0 = System.nanoTime();
                    BFS.run(g, start, showTree);
                    DFS.run(g, start, showTree);
                    long t1 = System.nanoTime();

                    double ms = (t1 - t0) / 1e6;
                    out.printf("Czas działania: %.3f ms%n", ms);
                    out.println();
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
