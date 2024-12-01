package GraphAnalysis;

import java.io.*;
import java.util.Scanner;

public class GraphLoader {
    public static Graph loadGraphFromFile(String fileName) {
        try (Scanner fileScanner = new Scanner(new File(fileName))){
            // Read the number of nodes
            int nodes = fileScanner.nextInt();
            Graph graph = new Graph(nodes);

            // Read the edges
            while (fileScanner.hasNextInt()) {
                int from = fileScanner.nextInt();
                int to = fileScanner.nextInt();
                int weight = fileScanner.nextInt();
                graph.addEdge(from, to, weight);
            }

            System.out.println("Graph successfully loaded from file: " + fileName);
            return graph;

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return null;
    }

    public static Graph loadGraphManually(Scanner scanner) {
        // Get the number of nodes
        System.out.println("Enter the number of nodes for the graph: ");
        int nodes = scanner.nextInt();
        Graph graph = new Graph(nodes);

        // Get the number of edges
        System.out.println("Enter edges in the format: 'from', 'to', 'weight' (Enter -1 to stop): ");
        while (true) {
            System.out.print("Enter 'from' edge: ");
            int from = scanner.nextInt();
            if (from == -1) break; // Stop condition

            System.out.print("Enter 'to' edge: ");
            int to = scanner.nextInt();

            System.out.print("Enter 'weight': ");
            int weight = scanner.nextInt();
            graph.addEdge(from, to, weight);
        }

        System.out.println("Graph successfully loaded manually.");
        return graph;
    }

}
