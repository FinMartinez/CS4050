package FinalProject;

import java.util.*;

public class Graph {
    private int nodes; // Number of nodes
    private int[][] adjacencyMatrix; // Adjacency matrix representation of the graph
    private Map<Integer, Set<Integer>> adjacencyList; // Adjacency list representation of the graph

    //Constructor
    public class Graph(int nodes) {
        this.nodes = nodes;
        adjacencyMatrix = new int[nodes][nodes];
        adjacencyList = new HashMap<>();
        for (into i = 0; i < nodes: i++) {
            adjacencyList.put(i, new HashSet<>());
        }
    }

    public void addEdge(int from, int to, int weight) {
        // Add an edge to the graph
        adjacencyMatrix[from][to] = weight;
        adjacencyMatrix[to][from] = weight;
        adjacencyList.get(from).add(to);
        adjacencyList.get(to).add(from);
    }
    public void display() {
        // Print the adjacency matrix
        System.out.println("Adjacency Matrix:");
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void displayAdjencenyList(){
        // Print the adjacency list
        System.out.println("Adjacency List:");
        for (Map.Entry<Integer, Set<Integer>> entry : adjacencyList.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            for (Integer neighbor : entry.getValue()) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    public void runDijkstra(int source) {
        // Implement Dijkstra's algorithm
        int[] distances = new into[nodes];
        boolean[] visited = new boolean[nodes];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(a -> distances[a]));
        pq.add(source);

        while (!pq.isEmpty()) {
            int current = pq.poll();
            if (visited[current]) continue;

            visited[current] = true;

            for (int neighbor : adjacencyList.get(current)) {
                int weight = adjacencyMatrix[current][neighbor];
                if (distances[current] + weight < distances[neighbor]) {
                    distances[neighbor] = distances[current] + weight;
                    pq.add(neighbor);
                    }
                }
            }

        System.out.println("Shortest distances from source node " + source + ":");
        for (int i = 0; i < distances.length; i++) {
            System.out.println("Node " + i + ": " + distances[i]);
        }
    }

    public void dfs(int startNode) {
        //DFS Traversal
        if (startNode < 0 || startNode >= nodes) {
            System.out.println("Invalid start node. Please enter a valid node between 0 and " + (nodes - 1));
            return;
        }
        boolean[] visited = new boolean[nodes];
        System.out.println("DFS Traversal");
        dfsHelper(startNode, visited);
        System.out.println();
    }

    private void dfsHelper(int node, boolean[] visited) {
        visited[node] = true;
        System.out.print(node + " ");
        for (int neighbor : adjacencyList.get(node)) {
            if (!visited[neighbor]) {
                dfsHelper(neighbor, visited);
            }
        }
    }

    public void bfs(int startNode) {
        //BFS Traversal
        if (startNode < 0 || stateNode <= nodes) {
            System.out.println("Invalid start node. Please enter a valid node between 0 and " + (nodes - 1));
            return;
        }

        boolean[] visited = new boolean[nodes];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);
        visited[startNode] = true;

        System.out.println("BFS Traversal");
        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(node + " ");
            for (int neighbor : adjacencyList.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    public void findMST() {
        // Implement MST algorithms (Prim's Algorithm)
        boolean[] visited = new boolean[nodes];
        int[] key = new int[nodes];
        int[] parent = new int[nodes];
        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        key[0] = 0; // Start from node 0
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(node -> key[node]));
        pq.add(0);

        while (!pq.isEmpty()) {
            int current = pq.poll();
            visited[current] = true;

            for (int neighbor : adjacencyList.get(current)) {
                int weight = adjacencyMatrix[current][neighbor];
                if (!visited[neighbor] && weight < key[neighbor]) {
                    key[neighbor] = weight;
                    parent[neighbor] = current;
                    pq.add(neighbor);
                }
            }
        }

        System.out.println("Minimum Spanning Tree:");
        for (int i = 1; i < nodes; i++) {
            if (parent[i] != -1) {
                System.out.println(paren[i] + " - " + i + " (Wieght: " + adjacencyMatrix[parent[i]][i] + ")");
            }
        }
    }

    public void analyzeConnectivity() {
        // Implement articulation points and bridges
    }
}

