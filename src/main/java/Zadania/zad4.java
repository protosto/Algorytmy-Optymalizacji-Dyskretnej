package Zadania;

import Graph.util.*;
import java.io.*;
import java.util.*;

public class zad4 {

    private static final String[] TEST_FILES = {
            "src/main/resources/AOD_Test1/4/d4a-1.txt",
            "src/main/resources/AOD_Test1/4/d4a-2.txt",
            "src/main/resources/AOD_Test1/4/d4a-3.txt",
            "src/main/resources/AOD_Test1/4/d4a-4.txt",
            "src/main/resources/AOD_Test1/4/d4a-5.txt",
            "src/main/resources/AOD_Test1/4/d4a-6.txt",
            "src/main/resources/AOD_Test1/4/d4b-1.txt",
            "src/main/resources/AOD_Test1/4/d4b-2.txt",
            "src/main/resources/AOD_Test1/4/d4b-3.txt",
            "src/main/resources/AOD_Test1/4/d4b-4.txt",
            "src/main/resources/AOD_Test1/4/d4b-5.txt",
            "src/main/resources/AOD_Test1/4/d4b-6.txt",
            "src/main/resources/AOD_Test1/4/u4a-1.txt",
            "src/main/resources/AOD_Test1/4/u4a-2.txt",
            "src/main/resources/AOD_Test1/4/u4a-3.txt",
            "src/main/resources/AOD_Test1/4/u4a-4.txt",
            "src/main/resources/AOD_Test1/4/u4a-5.txt",
            "src/main/resources/AOD_Test1/4/u4a-6.txt",
            "src/main/resources/AOD_Test1/4/u4b-1.txt",
            "src/main/resources/AOD_Test1/4/u4b-2.txt",
            "src/main/resources/AOD_Test1/4/u4b-3.txt",
            "src/main/resources/AOD_Test1/4/u4b-4.txt",
            "src/main/resources/AOD_Test1/4/u4b-5.txt",
            "src/main/resources/AOD_Test1/4/u4b-6.txt",
            "src/main/resources/AOD_Test1/4/myBipartiteGraph.txt",
            "src/main/resources/AOD_Test1/4/myNonBipartiteGraph.txt"
    };

    public static void main(String[] args) {

        String outputFile = "results4.txt";

        try (PrintWriter out = new PrintWriter(new FileWriter(outputFile))) {
            out.println("=== AO Lab1 - Zadanie 4: Sprawdzanie dwudzielności grafu ===");

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
                    List<List<Integer>> result = TwoColoring.run(g);
                    long t1 = System.nanoTime();
                    double ms = (t1 - t0) / 1e6;

                    if (result == null) {
                        out.println("Graf NIE jest dwudzielny.");
                    } else {
                        out.println("Graf JEST dwudzielny.");
                        if (g.size() <= 200) {
                            out.println("Podział na zbiory:");
                            out.println("V0 = " + result.get(0));
                            out.println("V1 = " + result.get(1));
                        }
                    }

                    out.printf("Czas działania: %.3f ms%n%n", ms);
                } catch (Exception e) {
                    out.println("Błąd podczas przetwarzania: " + e.getMessage());
                }
            }

            System.out.println("Wyniki zapisano do pliku: " + outputFile);
            out.println("------------------------------------------------------------");
            out.println("Koniec testów");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
