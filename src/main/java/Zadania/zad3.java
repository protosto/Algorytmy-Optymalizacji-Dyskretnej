package Zadania;

import Graph.util.*;
import java.io.*;
import java.util.*;

public class zad3 {

    private static final String[] TEST_FILES = {
            "src/main/resources/AOD_Test1/3/g3-1.txt",
            "src/main/resources/AOD_Test1/3/g3-2.txt",
            "src/main/resources/AOD_Test1/3/g3-3.txt",
            "src/main/resources/AOD_Test1/3/g3-4.txt",
            "src/main/resources/AOD_Test1/3/g3-5.txt",
            "src/main/resources/AOD_Test1/3/g3-6.txt",
            "src/main/resources/AOD_Test1/3/myConnectedGraph.txt",
            "src/main/resources/AOD_Test1/3/myStronglyConnectedGraph.txt"
    };

    public static void main(String[] args) {

        String outputFile = "results3.txt";

        try (PrintWriter out = new PrintWriter(new FileWriter(outputFile))) {
            out.println("=== AO Lab1 - Zadanie 3: Silnie spójne składowe (SCC) ===");

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
                    List<List<Integer>> components = StronglyConnectedComponents.run(g);
                    long t1 = System.nanoTime();

                    out.println("Liczba silnie spójnych składowych: " + components.size());
                    out.println("Rozmiary składowych:");
                    for (int i = 0; i < components.size(); i++) {
                        List<Integer> comp = components.get(i);
                        out.printf("SCC #%d: %d wierzchołków", i + 1, comp.size());
                        if (g.size() <= 200) {
                            out.print(" -> " + comp);
                        }
                        out.println();
                    }

                    double ms = (t1 - t0) / 1e6;
                    out.printf("Czas działania: %.3f ms%n%n", ms);
                }
            }

            System.out.println("Wyniki zapisano do pliku: " + outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}