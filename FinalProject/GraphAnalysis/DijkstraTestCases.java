package GraphAnalysis;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DijkstraTestCases {
    //Test classes to validate the implementation
    public void runTestsMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            //Main Menu
            System.out.println("Graph Theory Analysis - Test Menu");
            System.out.println("1. Small Directed Graph");
            System.out.println("2. Sparse Graph");
            System.out.println("3. Complete Graph");
            System.out.println("4. Disconnected Graph");
            System.out.println("5. Cyclic Graph");
            System.out.println("6. Exit Test Cases");

            int choice = getValidatedInput(scanner, "Enter your choice: ");
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    // Test Adjacency Matrix
                    runSmallDirectedGraph();
                    break;
                case 2:
                    // Test Dijkstra's Algorithm
                    runSparseGraph();
                    break;
                case 3:
                    // Test BFS Algorithm
                    runCompleteGraph();
                    break;
                case 4:
                    // Test DFS Algorithm
                    runDisconnectedGraph();
                    break;
                case 5:
                    // Test MST Algorithm
                    runCyclicGraph();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Exiting Test Cases...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid choice.");
                    break;
            }
        }
    }

    // Test Case 1: Small Directed Graph
    private void runSmallDirectedGraph() {
        System.out.println("Test Case 1: Small Directed Graph");
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 2);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 3, 7);
        graph.runDijkstra(0);
    }

    // Test Case 2: Sparse Graph
    private void runSparseGraph() {
        System.out.println("Test Case 2: Sparse Graph");
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 6);
        graph.addEdge(2, 3, 3);
        graph.runDijkstra(0);
    }

    // Test Case 3: Complete Graph
    private void runCompleteGraph() {
        System.out.println("Test Case 3: Complete Graph");
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 3);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 4);
        graph.addEdge(2, 3, 6);
        graph.runDijkstra(0);
    }
    // Test Case 4: Disconnected Graph
    private void runDisconnectedGraph() {
        System.out.println("Test Case 4: Disconnected Graph");
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 2);
        graph.addEdge(2, 3, 4);
        graph.runDijkstra(0);
    }

    // Test Case 5: Cyclic Graph
    private void runCyclicGraph() {
        System.out.println("Test Case 5: Cyclic Graph");
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 3, 3);
        graph.addEdge(3, 0, 1);
        graph.runDijkstra(0);
    }

    private static int getValidatedInput(Scanner scanner, String message) {

        while (true) {
            System.out.print(message);

            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }
}
