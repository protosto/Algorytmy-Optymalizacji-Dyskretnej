package Zadania;

import Graph.util.*;
import java.io.*;
import java.util.*;

public class zad2 {

    private static final String[] TEST_FILES = {
            "src/main/resources/AOD_Test1/2/g2a-1.txt",
            "src/main/resources/AOD_Test1/2/g2a-2.txt",
            "src/main/resources/AOD_Test1/2/g2a-3.txt",
            "src/main/resources/AOD_Test1/2/g2a-4.txt",
            "src/main/resources/AOD_Test1/2/g2a-5.txt",
            "src/main/resources/AOD_Test1/2/g2a-6.txt",
            "src/main/resources/AOD_Test1/2/g2b-1.txt",
            "src/main/resources/AOD_Test1/2/g2b-2.txt",
            "src/main/resources/AOD_Test1/2/g2b-3.txt",
            "src/main/resources/AOD_Test1/2/g2b-4.txt",
            "src/main/resources/AOD_Test1/2/g2b-5.txt",
            "src/main/resources/AOD_Test1/2/g2b-6.txt",
            "src/main/resources/AOD_Test1/2/myAcyclicGraph.txt",
            "src/main/resources/AOD_Test1/2/myCyclicGraph.txt"
    };

    public static void main(String[] args) {


        String outputFile = "results2.txt";

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
                    TopologicalSort.run(g);
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
