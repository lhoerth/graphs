import java.util.*;

/**
 * A representation of a graph.
 * Assumes that we do not have negative cost edges in the graph.
 */
public class MyGraph implements Graph {
    private Collection<Vertex> myVertices; // the vertices in this graph
    private Collection<Edge> myEdges;       // the edges in this graph

    /**
     * Creates a MyGraph object with the given collection of vertices
     * and the given collection of edges.
     *
     * @param v a collection of the vertices in this graph
     * @param e a collection of the edges in this graph
     */
    public MyGraph(Collection<Vertex> v, Collection<Edge> e) {

        // calculate indegrees of vertices
        for (Vertex w : v){
            for (Edge a : e) {
                if (a.to == w)
                    w.indegree++;
            }
        }

        myVertices = v;
        myEdges = e;
    }

    /**
     * Return the collection of vertices of this graph
     *
     * @return the vertices as a collection (which is anything iterable)
     */
    public Collection<Vertex> vertices() {
        return myVertices;
    }

    /**
     * Return the collection of edges of this graph
     *
     * @return the edges as a collection (which is anything iterable)
     */
    public Collection<Edge> edges() {
        return myEdges;
    }

    /**
     * Return a collection of vertices adjacent to a given vertex v.
     * i.e., the set of all vertices w where edges v -> w exist in the graph. (from == v)
     * Return an empty collection if there are no adjacent vertices.
     *
     * @param v one of the vertices in the graph
     * @return an iterable collection of vertices adjacent to v in the graph
     * @throws IllegalArgumentException if v does not exist.
     */
    public Collection<Vertex> adjacentVertices(Vertex v) {
        return outNeighbors(v);
    }

    /**
     * Return a collection of vertices that are reachable from a given vertex v.
     * A vertex is reachable if a path exists from v to the other vertex.
     *
     * @param v one of the vertices in the graph
     * @return an iterable collection of vertices that are reachable from v in the graph
     */
    public Collection<Vertex> reachableVertices(Vertex v) {

        // breadth first

        // hold result, all of v's reachable nodes
        Set<Vertex> result = new HashSet<Vertex>();

        // Empty sets for current and next frontiers
        Set<Vertex> frontier = new HashSet<Vertex>();
        Set<Vertex> nextFrontier = new HashSet<Vertex>();

        // a vertex is reachable to itself (path length 0)
        result.add(v);

        frontier.add(v);

        // while there are vertices in the current frontier
        while (!frontier.isEmpty()) {
            // to iterate through frontier
            Iterator itr = frontier.iterator();

            // until the last of the frontier
            while (itr.hasNext()) {
                // get current; cast to vertex because iterator.next returns Object
                Vertex current = (Vertex) itr.next();

                // out-neighbors of current
                Set<Vertex> outNodes = outNeighbors(current);

                // remove all out-neighbors we already visited (omit loops)
                // by removing all elements of result from outNodes of current
                outNodes.removeAll(result);

                // then copy all remaining outNodes of current to the next frontier
                nextFrontier.addAll(outNodes);
                outNodes.clear();
            }

            result.addAll(nextFrontier);

            frontier.clear();
            frontier.addAll(nextFrontier);
            nextFrontier.clear();
        }
        return result;
    }

    /*private Set<Vertex> depthReachable(Set<Vertex> set, Vertex v) {
        return null;
    }*/

    /**
     * Returns a topological sorting of the vertices in the graph.
     * Got help from Brandon
     *
     * @return an ordered list of vertices in topological sort order
     */
    public List<Vertex> topologicalSort() {
        /* Brandon's example (adjusted):
        List<Vertex> result = new LinkedList<Vertex>();

        boolean added = false;
        for (Vertex v : myVertices) {
            for (Vertex w : result) {
                // if v has fewer inNeighbors than w,
                if (inNeighbors(v).size() < inNeighbors(w).size()) {
                    // add v before w in result list
                    result.add(result.indexOf(w), v);
                    added = true;

                    // done checking current v against result vertices
                    break;
                }
            }
            if (added) {
                added = false;
            }
            else {
                result.add(v);
            }
        }
        return result;
        */

        // book's example (I adapted it from their pseudocode):
        List<Vertex> result = new LinkedList<Vertex>();
        Queue<Vertex> q = new LinkedList<Vertex>();

        int counter = 0;

        for (Vertex v : myVertices) {
            if (v.indegree == 0) {
                q.add(v);
            }
        }

        while (!q.isEmpty()){
            Vertex v = q.remove();
            result.add(v);
            v.topNum = ++counter; // assign next number

            for (Vertex w : adjacentVertices(v)){

                if (--w.indegree == 0){
                    q.add(w);
                }
            }
        }

        return result;
    }

    /**
     * Test whether vertex b is adjacent to vertex a (i.e. a -> b) in a directed graph.
     * Assumes that we do not have negative cost edges in the graph.
     *
     * @param a one vertex
     * @param b another vertex
     * @return cost of edge if there is a directed edge from a to b in the graph,
     * return -1 otherwise.
     * @throws IllegalArgumentException if a or b do not exist.
     */
    public int isAdjacent(Vertex a, Vertex b) {
        for (Edge e : myEdges){
            if (e.from == a && e.to == b){
                return e.w;
            }
        }
        return -1;
    }

    /**
     * Returns the shortest path from a to b in the graph.  Assumes positive
     * edge weights.  Uses Dijkstra's algorithm.
     *
     * @param a    the starting vertex
     * @param b    the destination vertex
     * @param path a list in which the path will be stored, in order, the first
     *             being the start vertex and the last being the destination vertex.  The
     *             list will be empty if no such path exists.  NOTE: the list will be
     *             cleared of any previous data.  path is not expected to contain any data
     *             needed by the method when the method is called.  It is used to allow
     *             us to return multiple values from the function.
     * @return the length of the shortest path from a to b, -1 if no such path
     * exists.
     * @throws IllegalArgumentException if a or b does not exist.
     */
    public int shortestPath(Vertex a, Vertex b, List<Vertex> path) {

        // YOUR CODE HERE
        return -1;
    }

    // helper method
    // When provided with a vertex called v
    // it should return a set of all the "incoming" neighbor vertices of v
    public Set<Vertex> inNeighbors(Vertex v) {
        Set<Vertex> result = new HashSet<Vertex>();
        // for every edge e in the myEdges set,
        for (Edge e : myEdges) {
            // if the "to" end of the edge matches the parameter,
            if (e.to.equals(v)) {
                // the other end is an "in-neighbor" and we add it to the result
                result.add(e.from);
            }
        }
        return result;
    }

    // When provided with a vertex called v
    // it should return a set of all the "outgoing" neighbor vertices of v
    public Set<Vertex> outNeighbors(Vertex v) {
        Set<Vertex> result = new HashSet<Vertex>();

        /* myEdges is a set of all edges in the graph and it is iterable so we an
            use it in a for each loop to check every edge we have.
         */
        // for every edge e in the myEdges set (entire graph),
        for (Edge e : myEdges) {
            // if the "from" end of the edge matches v, like (v->x),
            if (e.from.equals(v)) {
                // the other end of the edge is an "out-neighbor" and we add it to our result
                result.add(e.to);
            }
        }
        return result;
    }

    // return all nodes that have no in-neighbors
    public Set<Vertex> minNodes() {
        // create empty set
        Set<Vertex> result = new HashSet<Vertex>();

        // for each vertex in the graph
        for (Vertex node : myVertices) {
            // scan for any inNeighbors of current node
            Set<Vertex> inNbrs = inNeighbors(node);

            // if no inNeighbors found, add vertex to result list
            if (inNbrs.isEmpty()) {
                result.add(node);
            }
        }

        // return result list
        return result;
    }
}