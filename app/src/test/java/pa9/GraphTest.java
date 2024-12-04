/*
 * This source file was generated by the Gradle 'init' task
 */
package pa9;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;


class GraphTest {

    @Test
    void testAddWeightedEdge() {
        GraphAdjList g = new GraphAdjList(5);
        g.addWeightedEdge(0, 1, 2); 
        g.addWeightedEdge(0, 2, 4); 
        g.addWeightedEdge(1, 2, -1); 
        g.addWeightedEdge(2, 3, -3);
        int[] distances = g.shortestPath(0);

        assertEquals(0, distances[0]); 
        assertEquals(2, distances[1]); 
        assertEquals(1, distances[2]); 
        assertEquals(-2, distances[3]); 
    }

    @Test
    void testAddMoreEdges() {
        GraphAdjList g = new GraphAdjList(4);
        g.addWeightedEdge(0, 1, 5); 
        g.addWeightedEdge(1, 2, 3); 
        g.addWeightedEdge(0, 2, 10); 
        g.addWeightedEdge(2, 3, 2); 
        int[] distances = g.shortestPath(0);

        assertEquals(0, distances[0]); 
        assertEquals(5, distances[1]); 
        assertEquals(8, distances[2]); 
        assertEquals(10, distances[3]); 
    }

    @Test
    void testShortPath() {
        
        GraphAdjList g = new GraphAdjList(3);
        g.addWeightedEdge(0, 1, 3); 
        g.addWeightedEdge(1, 2, -2); 
        int[] distances = g.shortestPath(0);

        assertEquals(0, distances[0]); 
        assertEquals(3, distances[1]); 
        assertEquals(1, distances[2]); 
    }

    @Test
    void testNotConnected() {
        GraphAdjList g = new GraphAdjList(5);
        g.addWeightedEdge(0, 1, 5); 
        g.addWeightedEdge(1, 2, -3); 
        int[] distances = g.shortestPath(0);

        assertEquals(Integer.MAX_VALUE, distances[3]); 
        assertEquals(Integer.MAX_VALUE, distances[4]);
    }

    @Test
    void testNoNegativeCycle() {
        GraphAdjList g = new GraphAdjList(4);
        g.addWeightedEdge(0, 1, 5);  
        g.addWeightedEdge(1, 2, 3);  
        g.addWeightedEdge(2, 3, 2);  

        assertEquals(false, g.hasNegativeCycle());
    }

    @Test
    void testNegativeCycle1() {
        GraphAdjList g = new GraphAdjList(3);
        g.addWeightedEdge(0, 1, 1); 
        g.addWeightedEdge(1, 2, -2); 
        g.addWeightedEdge(2, 0, -2); 

        assertEquals(true, g.hasNegativeCycle()); 
    }

    @Test
    void testNegativeCycle2() {
        GraphAdjList g = new GraphAdjList(4);
        g.addWeightedEdge(0, 1, 2);
        g.addWeightedEdge(1, 2, -3);
        g.addWeightedEdge(2, 3, 1);
        g.addWeightedEdge(3, 0, -1);

        assertEquals(true, g.hasNegativeCycle());
    }

    @Test
    void testNegativeCycle3() {
        GraphAdjList g = new GraphAdjList(3);
        g.addWeightedEdge(0, 1, 4);
        g.addWeightedEdge(1, 2, -6);
        g.addWeightedEdge(2, 0, 3);

        assertEquals(false, g.hasNegativeCycle());
    }

    @Test
    void testNegativeCycle4() {
        GraphAdjList g = new GraphAdjList(5);
        g.addWeightedEdge(0, 1, 2);
        g.addWeightedEdge(1, 2, 3);
        g.addWeightedEdge(2, 3, -4);
        g.addWeightedEdge(3, 4, 5);
        g.addWeightedEdge(4, 0, -6);

        assertEquals(false, g.hasNegativeCycle());
    }

    @Test
    void testNegativeCycle5() {
        GraphAdjList g = new GraphAdjList(3);
        g.addWeightedEdge(0, 1, 5);
        g.addWeightedEdge(1, 2, -10);
        g.addWeightedEdge(2, 0, 6);

        assertEquals(false, g.hasNegativeCycle());
    }

    @Test
    void testNoNegativeCycles() {
        GraphAdjList g = new GraphAdjList(4);
        g.addWeightedEdge(0, 1, 2);
        g.addWeightedEdge(1, 2, 3);
        g.addWeightedEdge(2, 3, 1);
        g.addWeightedEdge(3, 0, 4);

        assertEquals(false, g.hasNegativeCycle());
    }

    @Test
    void testNoNegativeCycle2() {
        GraphAdjList g = new GraphAdjList(5);
        g.addWeightedEdge(0, 1, 2);
        g.addWeightedEdge(1, 2, 3);
        g.addWeightedEdge(2, 3, 4);
        g.addWeightedEdge(3, 4, 5);

        assertEquals(false, g.hasNegativeCycle());
    }

    @Test
    void testMinimumSpanningTreePrim() {
        GraphAdjList g = new GraphAdjList(4);
        g.addWeightedEdgeUndirected(0, 1, 1);
        g.addWeightedEdgeUndirected(1, 2, 2);
        g.addWeightedEdgeUndirected(2, 3, 3);
        g.addWeightedEdgeUndirected(0, 3, 4);
        
        int[] mst = g.minimumSpanningTreePrim();
        assertEquals(-1, mst[0]);
        assertEquals(0, mst[1]);
        assertEquals(1, mst[2]);
        assertEquals(2, mst[3]);
    }

    @Test
    void testMinimumSpanningTreePrim1() {
        GraphAdjList g = new GraphAdjList(5);
        g.addWeightedEdgeUndirected(0, 1, 2);
        g.addWeightedEdgeUndirected(0, 2, 3);
        g.addWeightedEdgeUndirected(0, 3, 4);
        g.addWeightedEdgeUndirected(0, 4, 5);
        
        int[] mst = g.minimumSpanningTreePrim();
        
        assertEquals(-1, mst[0]);
        assertEquals(0, mst[1]);
        assertEquals(0, mst[2]);
        assertEquals(0, mst[3]);
        assertEquals(0, mst[4]);
    }

    @Test
    void testMinimumSpanningTreePrim2() {
        GraphAdjList g = new GraphAdjList(5);
        g.addWeightedEdgeUndirected(0, 1, 1);
        g.addWeightedEdgeUndirected(1, 2, 1);
        g.addWeightedEdgeUndirected(2, 3, 1);
        g.addWeightedEdgeUndirected(3, 4, 1);
        int[] mst = g.minimumSpanningTreePrim();
    
        assertEquals(-1, mst[0]); 
        assertEquals(0, mst[1]);  
        assertEquals(1, mst[2]);  
        assertEquals(2, mst[3]);  
        assertEquals(3, mst[4]);  
    }

    @Test
    void testMinimumSpanningTreePrim3() {
        GraphAdjList g = new GraphAdjList(1);
        int[] mst = g.minimumSpanningTreePrim();
        assertEquals(-1, mst[0]);
    }

    @Test
    void testMinimumSpanningTreePrim4(){
        GraphAdjList g = new GraphAdjList(4);
        g.addWeightedEdgeUndirected(0, 1, 1);
        g.addWeightedEdgeUndirected(1, 2, 2);
        g.addWeightedEdgeUndirected(2, 3, 3);
        g.addWeightedEdgeUndirected(3, 0, 4);
        int[] mst = g.minimumSpanningTreePrim();

        assertEquals(-1, mst[0]);
        assertEquals(0, mst[1]);
        assertEquals(1, mst[2]);
        assertEquals(2, mst[3]);
    }

    @Test
    public void testKruskal() {
        GraphAdjList graph = new GraphAdjList(4);
        HashSet<GraphAdjList.Edge> mst = graph.minimumSpanningTree();
        assertEquals(true, mst.isEmpty());
    }

    @Test
    public void testKruskal1(){
        // creates graph and adds edges
        GraphAdjList graph = new GraphAdjList(5);
        graph.addWeightedEdge(0, 1, 1);
        graph.addWeightedEdge(1, 2, 2);
        graph.addWeightedEdge(2, 3, 3);
        graph.addWeightedEdge(3, 4, 4);
        graph.addWeightedEdge(0, 4, 5);
        
        // creates minimum spanning tree
        HashSet<GraphAdjList.Edge> mst = graph.minimumSpanningTree();
        boolean E1 = false;
        boolean E2 = false;
        boolean E3 = false;
        boolean E4 = false;
        // going over each edge in the mst to check if it contains the correct edges
        for (GraphAdjList.Edge edge : mst) {
            if (edge.source == 0 && edge.destination == 1 && edge.weight == 1) {
                E1 = true;
            }
            if (edge.source == 1 && edge.destination == 2 && edge.weight == 2) {
                E2 = true;
            }
            if (edge.source == 2 && edge.destination == 3 && edge.weight == 3) {
                E3 = true;
            }
            if (edge.source == 3 && edge.destination == 4 && edge.weight == 4) {
                E4 = true;
            }
        }
        // if the edge is in the graph(hashSet) it should be switched to true
        assertEquals(true, E1);
        assertEquals(true, E2);
        assertEquals(true, E3);
        assertEquals(true, E4);
    }

    @Test
    public void testKruskal2() {
        GraphAdjList graph = new GraphAdjList(4);
        graph.addWeightedEdge(0, 1, 1);
        graph.addWeightedEdge(0, 1, 2);
        graph.addWeightedEdge(1, 2, 1);
        graph.addWeightedEdge(2, 3, 1);
        HashSet<GraphAdjList.Edge> mst = graph.minimumSpanningTree();
        boolean E1 = false;
        boolean E2 = false;
        boolean E3 = false;
        boolean E4 = false;
        for (GraphAdjList.Edge edge : mst) {
            if (edge.source == 0 && edge.destination == 1 && edge.weight == 1) {
                E1 = true;
            }
            if (edge.source == 1 && edge.destination == 2 && edge.weight == 1) {
                E2 = true;
            }
            if (edge.source == 2 && edge.destination == 3 && edge.weight == 1) {
                E3 = true;
            }
        }
        assertEquals(true, E1);
        assertEquals(true, E2);
        assertEquals(true, E3);
        assertEquals(false, E4);
    }
}