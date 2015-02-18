/**
 * test myvertices.java
 */
import java.util.*;

public class GraphTester {
    public static void main(String[] args) {
        Set<Vertex> vertices = new HashSet<Vertex>();
        Set<Edge> edges = new HashSet<Edge>();
        
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        Vertex v5 = new Vertex("5");
        Vertex v6 = new Vertex("6");
        Vertex v7 = new Vertex("7");

        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);
        vertices.add(v5);
        vertices.add(v6);
        vertices.add(v7);

        //edges
        // from v1
        edges.add(new Edge(v1,v2, 0));
        edges.add(new Edge(v1,v3,0));
        edges.add(new Edge(v1,v4,0));

        // from v2
        edges.add(new Edge(v2, v4, 0));
        edges.add(new Edge(v2, v5, 0));

        // from v3
        edges.add(new Edge(v3, v6, 0));

        // from v4
        edges.add(new Edge(v4, v3, 0));
        edges.add(new Edge(v4, v6, 0));
        edges.add(new Edge(v4, v7, 0));

        // from v5
        edges.add(new Edge(v5, v4, 0));
        edges.add(new Edge(v5, v7, 0));

        // (none from v6)

        // from v7
        edges.add(new Edge(v7, v6, 0));


        MyGraph graph = new MyGraph(vertices, edges);

        System.out.println("Graph Tester");
        System.out.println("\nTesting MyGraph.vertices()");
        System.out.println(graph.vertices());

        System.out.println("\nTesting edges()");
        System.out.println(graph.edges());

        System.out.println("\nTesting adjacentVertices(v4)");
        // should yield 3, 6, and 7
        System.out.println(graph.adjacentVertices(v4));

        System.out.println("\nTesting reachableVertices(v5)");
        // should yield 3, 4, 3, 6, and 7
        System.out.println(graph.reachableVertices(v5));

        System.out.println("\nTesting topologicalSort()");
        // should yield (1, 2, 5, 4, {3,7}, 6)
        System.out.println(graph.topologicalSort());

        System.out.println("\nTesting isAdjacent(v6,v3)");
        System.out.println(graph.isAdjacent(v6, v3));

        System.out.println("\nTesting isAdjacent(v4,v6)");
        System.out.println(graph.isAdjacent(v4,v6));


    }
}
