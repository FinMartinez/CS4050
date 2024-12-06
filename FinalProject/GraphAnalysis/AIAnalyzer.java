package GraphAnalysis;

import java.util.*;

public class AIAnalyzer {
    
    private Graph graph; //Source graph for analysis
    private int sourceNode; //Source node for algorithms
    private int targetNode; //Optional target for route-finding

    //Constructor
    public AIAnalyzer(Graph graph, int sourceNode, int targetNode){
        this.graph = graph;
        this.sourceNode = sourceNode;
        this.targetNode = targetNode;
    }

    //Run + Analyze algorithms
    public void analyze() {
        Map<String, Result> results = new HashMap<>();

        //Run Dijkstra
        System.out.println("Running Dijkstra's Algorithm...");
        long startTime = System.nanoTime();
        int dijkstraCost = runDijkstra();
        long endTime = System.nanoTime();
        results.put("Dijkstra", new Result(dijkstraCost, (endTime - startTime)));

        //Run BFS
        System.out.println("Running BFS...");
        startTime = System.nanoTime();
        int bfsCost = runBFS();
        endTime = System.nanoTime();
        results.put("BFS", new Result(bfsCost, (endTime - startTime)));
        
        //Run DFS
        System.out.println("Running MST...");
        startTime = System.nanoTime();
        int dfsCost = runDFS();
        endTime = System.nanoTime();
        results.put("DFS", new Result(dfsCost, (endTime - startTime)));

        //Run MST
        System.out.println("Running MST...");
        startTime = System.nanoTime();
        int mstCost = runMST();
        endTime = System.nanoTime();
        results.put("MST", new Result(mstCost, (endTime - startTime)));

        //Recommend best alg
        recommendAlgorithm(results);
    }

    //Dijkstra's Alg
    private int runDijkstra(){
        return graph.runDijkstra(sourceNode);
    }

    //BFS
    private int runBFS(){
        return graph.bfsCost(sourceNode, targetNode);
    }

    //DFS
    private int runDFS(){
        return graph.dfsCost(sourceNode, targetNode);
    }

    //MST
    private int runMST(){
        return graph.findMSTCost();
    }

    //Recommend best algorithm
    private void recommendAlgorithm(Map<String, Result> results){
        String bestAlgorithm = null;
        double bestEfficiency = Double.MAX_VALUE;

        for (Map.Entry<String, Result> entry : results.entrySet()){
            String algorithm = entry.getKey();
            Result result = entry.getValue();

            //Calculate efficiency
            double efficiency = result.cost * (result.runTime / 1e6); //Convert runtime to ms
            System.out.println(algorithm + " Efficiency: " + efficiency);

            if (efficiency < bestEfficiency){
                bestEfficiency = efficiency;
                bestAlgorithm = algorithm;
            }
        }

        System.out.println("Recommended Algorithm: " + bestAlgorithm);
    }

    //Helper class for storing results
    private static class Result {
        int cost; //Total cost
        long runTime; //Execution in ns

        public Result(int cost, long runTime){
            this.cost = cost;
            this.runTime = runTime;
        }
    }
}
