
package GraphAnalysis;

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
            System.out.println("4. Find Minimum Spanning Tree (MST)");
            /* 
             * System.out.println("5. Search Recommendation");
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
                    System.out.println("Chose input mode (file/manual): ");
                    String inputMode = scanner.nextLine().toLowerCase();
                    
                    if (inputMode.equals("file")) {
                        System.out.println("Enter the file name: ");
                        String fileName = scanner.nextLine();
                        graph = GraphLoader.loadGraphFromFile(fileName);
                    } else if (inputMode.equals("manual")) {
                        graph = GraphLoader.loadGraphManually(scanner);
                    } else {
                        System.out.println("Invalid input mode. Please enter a valid input mode.");
                    }
                    System.out.println("Graph data loaded successfully.");
                    break;
                case 2:
                    // Display adjacency matrix and list
                    if (graph != null) {
                        graph.displayAdjencenyMatrix();
                        graph.displayAdjencenyList();
                    } else {
                        System.out.println("No graph data loaded. Please load graph data first.");
                    }
                    break;
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
                case 4:
                    // Find MST
                    if (graph != null) {
                        graph.findMST(); // Method for MST (Prim's or Kruskal's?)
                    } else {
                        System.out.println("No graph data loaded. Please load graph data first.");
                    }
                    break;
                /*
                case 5:
                    // Search Recommenation
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
                    // Test Cases
                    System.out.println("Test case 1: Small Directed Graph:");
                    Graph testGraph1 = new Graph(4);
                    testGraph1.addEdge(0, 1, 4);





                    Graph testGraph = new Graph(6);
                    
                    //Add edges
                    testGraph.addEdge(0, 1, 4);
                    testGraph.addEdge(0, 2, 3);
                    testGraph.addEdge(1, 2, 2);
                    testGraph.addEdge(1, 3, 5);
                    testGraph.addEdge(2, 4, 1);
                    testGraph.addEdge(3, 4, 2);
                    testGraph.addEdge(4, 5, 6);

                    //testGraph = GraphLoader.loadGraph("test.txt");
                    testGraph.displayAdjencenyMatrix();
                    testGraph.displayAdjencenyList();
                    testGraph.runDijkstra(0);
                    testGraph.findMST();
                    testGraph.dfs(0);
                    testGraph.bfs(0);
                    break;
                case 8:
                    //AI Analysis Test Case
                    System.out.println("Running AI Analysis...");

                    //Load graph
                    Graph aiGraph = new Graph(6);
                    aiGraph.addEdge(0, 1, 4);
                    aiGraph.addEdge(0, 2, 3);
                    aiGraph.addEdge(1, 2, 2);
                    aiGraph.addEdge(1, 3, 5);
                    aiGraph.addEdge(2, 4, 1);
                    aiGraph.addEdge(3, 4, 2);
                    aiGraph.addEdge(4, 5, 6);

                    //Creat AIAnalyzer
                    AIAnalyzer analyzer = new AIAnalyzer(aiGraph, 0, 5);
                    analyzer.analyze();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid choice.");
            }
        }

        scanner.close();
    }
}
