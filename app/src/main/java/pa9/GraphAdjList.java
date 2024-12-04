package pa9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class GraphAdjList {
    Map<Integer, ArrayList<Pair<Integer, Integer>>> map;
    int nodes;

    public GraphAdjList(int nodes){
        map = new HashMap<>();
        this.nodes = nodes;
    }

    public void addWeightedEdge(int v, int w, int weight){
        if (!this.map.containsKey(v)) {
            ArrayList<Pair<Integer, Integer>> list = new ArrayList<>();
            list.add(new Pair<>(w, weight));
            this.map.put(v, list);
        }
        else {
            this.map.get(v).add(new Pair<>(w, weight));
        }
    }

    public void addWeightedEdgeUndirected(int v, int w, int weight){
        if (!this.map.containsKey(v)) {
            ArrayList<Pair<Integer, Integer>> list = new ArrayList<>();
            list.add(new Pair<>(w, weight));
            this.map.put(v, list);
        }
        else {
            this.map.get(v).add(new Pair<>(w, weight));
        }
        if (!this.map.containsKey(w)) {
            ArrayList<Pair<Integer, Integer>> list = new ArrayList<>();
            list.add(new Pair<>(v, weight));
            this.map.put(w, list);
        } 
        else{
            this.map.get(w).add(new Pair<>(v, weight));
        }
    }

    public static class Edge implements Comparable<Edge>{
        int source;
        int destination;
        int weight;
    
        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    
        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    public boolean hasNegativeCycle() {
        int[] distance = new int[nodes];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[0] = 0; 

        // adds list of edges from the adjacency map
        List<Edge> edges = new ArrayList<>();
        for (int u = 0; u < nodes; u++) {
            if (map.containsKey(u)) {
                ArrayList<Pair<Integer, Integer>> adjList = map.get(u);
                for (Pair<Integer, Integer> pair : adjList) {
                    int w = pair.getKey();
                    int weight = pair.getValue();
                    edges.add(new Edge(u, w, weight));
                }
            }
        }

        // relaxes edges repeatedly n-1 times
        for (int i = 0; i < nodes - 1; i++) {
            for (Edge edge : edges) {
                int u = edge.source;
                int w = edge.destination;
                int weight = edge.weight;
                if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[w]) {
                    distance[w] = distance[u] + weight;
                }
            }
        }

        // checks for negative weight cycle by trying to relax edges one last time
        for (Edge edge : edges) {
            int u = edge.source;
            int w = edge.destination;
            int weight = edge.weight;
            // if edge is relaxed, there is a negative cycle
            if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[w]) {
                return true;
            }
        }
        return false;
    }
    
    

    public int[] shortestPath(int v){
        int[] distance = new int[nodes];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[v] = 0;
        
        // adds edges to the map
        List<Edge> edges = new ArrayList<>();
        for (int u = 0; u < nodes; u++) {
            if (map.containsKey(u)) {
                ArrayList<Pair<Integer, Integer>> adjList = map.get(u);
                for (Pair<Integer, Integer> pair : adjList) {
                    int w = pair.getKey();  
                    int weight = pair.getValue(); 
                    edges.add(new Edge(u, w, weight));
                }
            }
        }

        // relaxes the edges n-1 times
        for (int i = 0; i < nodes - 1; i++) {
            for (Edge edge : edges) {
                int u = edge.source;
                int w = edge.destination;
                int weight = edge.weight;
                if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[w]) {
                    distance[w] = distance[u] + weight;
                }
            }
        }

        return distance;
    }


    public HashSet<Edge> minimumSpanningTree(){


        // Sort edges in non-decreasing order of weight
                //write loop to get all edges, sort by the weight
                    // use compareTo (edges.sort(Edge::compareTo))
        List<Edge> edges = new ArrayList<>();
        for (int u = 0; u < nodes; u++) {
            if (map.containsKey(u)) {
                for (Pair<Integer, Integer> pair : map.get(u)) {
                    int v = pair.getKey();
                    int weight = pair.getValue();
                    edges.add(new Edge(u, v, weight));
                }
            }
        }
        edges.sort(Edge::compareTo);
        int[] parent = new int[nodes];
        int[] size = new int[nodes];

        for (int i = 0; i < nodes; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        // go over list in order, t.add edge
        // if adding edge does not form cycle 
        // Store the MST in an empty graph
        // For each edge in the sorted list:
        // if adding the edge does not form a cycle
        // add the edge to the sorted list
        HashSet<Edge> mst = new HashSet<>();
        for (Edge edge : edges) {
            int u = edge.source;
            int v = edge.destination;

            int p1 = u;
            while (parent[p1] != p1) {
                p1 = parent[p1];
            }

            int p2 = v;
            while (parent[p2] != p2) {
                p2 = parent[p2];
            }
            if (p1 != p2) {
                mst.add(edge);

                
                if (size[p1] > size[p2]) {
                    parent[p2] = p1;  
                    size[p1] += size[p2];  
                } else {
                    parent[p1] = p2;  
                    size[p2] += size[p1];  
                }
            }
        }
        return mst;
    }
    


    public int[] minimumSpanningTreePrim() {
        // min heap
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        // track node in MST
        boolean[] inMST = new boolean[nodes]; 
        int[] parent = new int[nodes]; 
        // smallest weight edge to each node
        int[] key = new int[nodes]; 

        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0; 
        Arrays.fill(parent, -1); 

        pq.add(new Edge(-1, 0, 0));

        // Prims algorithm
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int u = edge.destination;

            //avoids repetition
            if (inMST[u]){
                continue;
            }
            inMST[u] = true;

            // updates the key values for neighbor nodes
            if (map.containsKey(u)) {
                for (Pair<Integer, Integer> pair : map.get(u)) {
                    int v = pair.getKey();
                    int weight = pair.getValue();
                    // is v is not in the MST and u -> v has a smaller weight update your key
                    if (!inMST[v] && weight < key[v]) {
                        key[v] = weight;
                        parent[v] = u;
                        pq.add(new Edge(u, v, weight));
                    }
                }
            }
        }
        return parent;
    }

}

