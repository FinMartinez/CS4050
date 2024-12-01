
package FinalProject;

import java.util.Scanner;

public class Driver {
    
    public static void main(String[] args) {
        /* 
        // These were suggested, might be useful for testing or implementnation
        HyperRingGraph graph = new HyperRingGraph(16);
        graph.displayGraph();
        graph.displayAdjencenyMatrix();
        graph.dfs(0);
        */

        Graph graph = null; // My Graph Class (adjacency matrix)
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            //Main Menu
            System.out.println("Graph Theory Analysis - Main Menu");
            System.out.println("1. Load Graph Data"); //Manual or by file?
            System.out.println("2. Display Graph Data");
            System.out.println("3. Run Dijkstra's Algorithm");
            /*
             * System.out.println("4. Find Minimum Spanning Tree (MST)");
             * System.out.println("5. Connectivity Analysis (Articulation Points, Bridges)");
             */
            System.out.println("6. Exit");

            //Test Case
            System.out.println("7. Run Test");
            //End Test Case

            System.out.println("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    // Load Graph Data
                    System.out.println("Enter file name or provide manual input: ");
                    String input = scanner.nextLine();
                    graph = GraphLoader.loadGraph(input); //Call helper class to load graph
                    System.out.println("Graph data loaded successfully.");
                    break;
                case 2:
                    // Display graph
                    if (graph != null) {
                        graph.displayGraph(); //Implement a method to display the adjacency matrix
                    } else {
                        System.out.println("No graph data loaded. Please load graph data first.");
                    }
                case 3:
                    // Run Dijkstra's Algorithm
                    if (graph != null) {
                        System.out.println("Enter the source node: ");
                        int source = scanner.nextInt();
                        graph.runDijkstra(source);
                    } else {
                        System.out.println("No graph data loaded. Please load graph data first.");
                    }
                    break;
                /*
                case 4:
                    // Find MST
                    if (graph != null) {
                        graph.findMST(); // Method for MST (Prim's or Kruskal's?)
                    } else {
                        System.out.println("No graph data loaded. Please load graph data first.");
                    }
                    break;
                case 5:
                    // Connectivity Analysis
                    if (graph != null) {
                        graph.connectivityAnalysis(); // Method for articulation points and bridges
                    } else {
                        System.out.println("No graph data loaded. Please load graph data first.");
                    }
                 */
                case 6:
                    // Exit
                    exit = true;
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                case 7:
                    // Test Case
                    System.out.println("Running test case...");

                    Graph graph = new Graph(5);
                    
                    graph = GraphLoader.loadGraph("test.txt");
                    graph.displayGraph();
                    graph.runDijkstra(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid choice.");
            }
        }

        scanner.close();
    }
}
