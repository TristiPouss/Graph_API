import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
<<<<<<< HEAD
 * The class UndirectedGraph is a representation of a undirected graph with an Adjacency Edge list
=======
 * An extend of the class Graph to represent an undirected graph
>>>>>>> 23af37922c755f540ec8465cb85e9540fa6ee285
 * @author Tristan de Saint Gilles
 * @author Renaud Joffrin
 */
public class UndirectedGraph extends Graph{

    /* Constructors */

    /**
     * Constructor for empty graph
     */
    public UndirectedGraph() {
        super();
    }
    
    /**
     * Constructor from Successor array
     * @param sa unknown number of int as successor array
     */
    public UndirectedGraph(int ... sa) {
        super(sa);
    }

    /* API */
    
    /**************************
     *                        *
     * Nodes related methods  *
     *                        *
     **************************/

     /**
     * for knowing the in-degree of node n. 
     * @param n a Node
     * @return an int
     */
    @Override
    public int inDegree(Node n){
        return degree(n);
    }

    /**
     * for knowing the in-degree of node n. 
     * @param n an int
     * @return an int
     */
    @Override
    public int inDegree(int n){
        return degree(getNode(n));
    }

    /**
     * for knowing the out-degree of node n. 
     * @param n a Node
     * @return an int
     */
    @Override
    public int outDegree(Node n){
        return degree(n);
    }

    /**
     * for knowing the out-degree of node n. 
     * @param n an int
     * @return an int
     */
    @Override
    public int outDegree(int n){
        return degree(getNode(n));
    }

     @Override
    public int degree(Node n){
        return getOutEdgesForDegrees(n).size();
    }

    @Override
    public int degree(int n){
        return getOutEdgesForDegrees(n).size();
    }

    /**************************
     *                        *
     * Edges related methods  *
     *                        *
     **************************/

     /**
     * 
     * @return the number of edges in this graph
     */
    @Override
    public int nbEdges(){
        int size = 0;
        for (var l : getList().values()){
            for (Edge e : l){
                if (dev) System.out.println(e.from().getId());
                size ++;
            }
        }
        return size/2;
    }

    /**
     * Add an edge in the graph, if any Node does not exist, they have to
     * be created first.
     * @param from a Node
     * @param to a Node
     */
    @Override
    public void addEdge(Node from, Node to){
        super.addEdge(from, to);
        super.addEdge(to, from);
    }

    /**
     * Add an edge in the graph, if any Node does not exist, they have to
     * be created first.
     * @param from a Node
     * @param to a Node
     * @param weight the weight of the edge
     */
    @Override
    public void addEdge(Node from, Node to, int weight){
        super.addEdge(from, to, weight);
        super.addEdge(to, from, weight);
    }

    /**
     * Add an edge in the graph, if any Node does not exist, they have to
     * be created first.
     * @param from a Node id
     * @param to a Node id
     */
    @Override
    public void addEdge(int from, int to){
        super.addEdge(from, to);
        super.addEdge(to, from);
    }

    /**
     * Add an edge in the graph, if any Node does not exist, they have to
     * be created first.
     * @param from a Node id
     * @param to a Node id
     * @param weight the weight of the edge
     */
    @Override
    public void addEdge(int from, int to, int weight){
        super.addEdge(from, to, weight);
        super.addEdge(to, from, weight);
    }

    /**
     * Add an edge in the graph
     * @param e Edge reference
     */
    @Override
    public void addEdge(Edge e){
        if(!e.isWeighted()) super.addEdge(e.from(), e.to());
        else super.addEdge(e.from(), e.to(), e.getWeight());

        if(!e.isWeighted()) super.addEdge(e.to(), e.from());
        else super.addEdge(e.to(), e.from(), e.getWeight());
    }

    /**
     * Remove the edge between @from and @to from this graph 
     * @param from a Node
     * @param to a Node
     * @return true if removal was a success
     *         false else.
     */
    @Override
    public boolean removeEdge(Node from, Node to){
        return super.removeEdge(from, to) && super.removeEdge(to, from);
    }

    /**
     * Remove the edge between @from and @to from this graph 
     * @param from a Node
     * @param to a Node
     * @param weight the weight of the edge
     * @return true if removal was a success
     *         false else.
     */
    @Override
    public boolean removeEdge(Node from, Node to, int weight){
        return super.removeEdge(from, to, weight) && super.removeEdge(to, from, weight);
    }

    /**
     * Remove the edge between @from and @to from this graph 
     * @param from a Node id
     * @param to a Node id
     * @return true if removal was a success
     *         false else.
     */
    @Override
    public boolean removeEdge(int from, int to){
        return super.removeEdge(from, to) && super.removeEdge(to, from);
    }

    /**
     * Remove the edge between @from and @to from this graph 
     * @param from a Node id
     * @param to a Node id
     * @param weight the weight of the edge
     * @return true if removal was a success
     *         false else.
     */
    @Override
    public boolean removeEdge(int from, int to, int weight){
        return super.removeEdge(from, to, weight) && super.removeEdge(to, from, weight);
    }

    /**
     * Remove the edge @e from this graph 
     * @param e the edge reference to remove
     * @return true if removal was a success
     *         false else.
     */
    @Override
    public boolean removeEdge(Edge e){
        if(!e.isWeighted())return super.removeEdge(e.from(), e.to()) && super.removeEdge(e.to(), e.from());
        else return super.removeEdge(e.from(), e.to(), e.getWeight()) && super.removeEdge(e.to(), e.from(), e.getWeight());
    }

    /**
     * For getting the list of edges leaving the Node @n
     * @param n a Node 
     * @return the list of edges leaving the node 
     */
    @Override
    public List<Edge> getOutEdges(Node n){
        if (getList().get(n) == null) return new ArrayList<>();
        List<Edge> edges = getList().get(n);
        List<Edge> result = new ArrayList<>();
        boolean add = false;

        for(Edge e : edges){
            if(e.from() != e.to()){
                result.add(e);
            }else{
                if(!add){
                    result.add(e);
                    add = true;
                }else{
                    add = false;
                }
            }
        }
        Collections.sort(result);
        return result;
    }

    /**
     * For getting the list of edges leaving the Node @n
     * @param n a Node id
     * @return the list of edges leaving the node 
     */
    @Override
    public List<Edge> getOutEdges(int n){
        return getOutEdges(getNode(n));
    }

    /**
     * For getting the list of edges leaving the Node @n
     * @param n a Node 
     * @return the list of edges leaving the node 
     */
    public List<Edge> getOutEdgesForDegrees(Node n){
        if (getList().get(n) == null) return new ArrayList<>();
        return getList().get(n);
    }

    /**
     * For getting the list of edges leaving the Node @n
     * @param n a Node id
     * @return the list of edges leaving the node 
     */
    public List<Edge> getOutEdgesForDegrees(int n){
        return getOutEdgesForDegrees(getNode(n));
    }

    /**
     * For getting the list of edges entering the Node @n
     * @param n a Node 
     * @return the list of edges entering the node 
     */
    @Override
    public List<Edge> getInEdges(Node n){
        return getOutEdges(n);
    }

    /**
     * For getting the list of edges entering the Node @n
     * @param n a Node id
     * @return the list of edges entering the node 
     */
    @Override
    public List<Edge> getInEdges(int n){
        return getInEdges(getNode(n));
    }

    /**
     * for getting the list of all edges incident to node @n. 
     * This is the union of the out and in edges
     * @param n a Node 
     * @return the list of edges entering and leaving the node 
     */
    @Override
    public List<Edge> getIncidentEdges(Node n){
        return getOutEdges(n);
    }

    /**
     * for getting the list of all edges incident to node @n. 
     * This is the union of the out and in edges
     * @param n a Node id
     * @return the list of edges entering and leaving the node 
     */
    @Override
    public List<Edge> getIncidentEdges(int n){
        return getIncidentEdges(getNode(n));
    }

    /**************************
     *                        *
     *   Representation and   *
     * Transformation methods *
     *                        *
     **************************/

     /**
      * for obtaining a representation of the graph in the SA (successor array) formalism
      * For every nodes, iterate through its adjacency edge list, and for each edge,
      * add the @to Node id in the result array
      * After checking every edge of a node, simply add a 0 in the result array
      * @return an array of int
      */
    @Override
    public int[] toSuccessorArray(){
        int[] result = new int[nbEdges() + nbNodes()];
        int count = 0;
        for (Node curr : getAllNodes()) {
            List<Edge> successors = getOutEdges(curr);
            for (Edge currEdge : successors) {
                if(currEdge.from().getId() <= currEdge.to().getId()){
                    result[count] = currEdge.to().getId();
                    count++;
                }
            }
            result[count] = 0;
            count++;
        }

        return result;
    }

    /**
     * for obtaining a representation of the graph as an adjacency matrix. Multigraphs are
     * allowed, so the elements in the matrix may be greater than 1, indicating the number of edges between any two
     * nodes. Also graphs with self-loops are allowed, thus allowing nonzero diagonal elements
     * The method consist to iterate through every edge of the graph and increment by one the number 
     * at the position [from-1][to-1] of the matrix
     * @return a matrix of int
     */
    @Override
    public int[][] toAdjMatrix(){
        int[][] adjMatrix = new int[nbNodes()][nbNodes()];
        boolean add = false;
        for (Edge e : super.getAllEdges()){
            if(e.from() == e.to()){
                if(!add){
                    adjMatrix[e.from().getId()-1][e.to().getId()-1]++;
                    add = true;
                }else{
                    add = false;
                }
            }else{
                adjMatrix[e.from().getId()-1][e.to().getId()-1]++;
            }
            
        }
        return adjMatrix;
    }

    @Override
    public UndirectedGraph getReverse(){
        return copy();
    }

    @Override
    public UndirectedGraph copy(){
        UndirectedGraph copy = new UndirectedGraph();
        boolean add = false;

        for (Edge e : super.getAllEdges()) {
            if(e.from().getId() <= e.to().getId()){
                if(e.from() == e.to()){
                    if(!add){
                        if (!e.isWeighted()) copy.addEdge(new Edge(e.from().getId(), e.to().getId(), copy));
                        else copy.addEdge(new Edge(e.from().getId(), e.to().getId(), e.getWeight(), copy));
                        add = true;
                    }else{
                        add = false;
                    }
                }else{
                    if (!e.isWeighted()) copy.addEdge(new Edge(e.from().getId(), e.to().getId(), copy));
                    else copy.addEdge(new Edge(e.from().getId(), e.to().getId(), e.getWeight(), copy));
                }
            }
        }

        return copy;
    }

    /**
     * for getting the list of all the edges of the graph.
     * @return the list of all edges in the graph.
     */
    @Override
    public List<Edge> getAllEdges(){
        boolean add = false;
        List<Edge> res = new ArrayList<>();
        for (List<Edge> edges : getList().values()) {
            for (Edge e : edges) {
                if(e.from().getId() <= e.to().getId()){
                    if(e.to() == e.from()){
                        if(!add){
                            res.add(e);
                            add = true;
                        }else{
                            add = false;
                        }
                    }else{
                        res.add(e);
                    }
                }
            }
        }
        Collections.sort(res);
        return res;
    }

    @Override
    public String toDotString() {
        String dotString = "digraph G {";

        List<Node> usedNodes = getAllNodesInEdges();

        for (Node n : getAllNodes()){
            if(getList().get(n).isEmpty()){
                if(!usedNodes.contains(n)){
                    dotString += "\n\t" + n;
                }
            }else{
                for (Edge e : getOutEdges(n)){
                    if(e.from().getId() <= e.to().getId()){
                        dotString += "\n\t" + e.from() + " -- " + e.to();
                        if(e.isWeighted()) dotString += " [label=" + e.getWeight() + ", len=" + e.getWeight() + "]";
                    }
                }
            }
        }

        dotString += "\n}";
        return dotString;
    }

    @Override
    public UndirectedGraph getTransitiveClosure() {
        UndirectedGraph transClosure = new UndirectedGraph();

        for (Node u : getAllNodes()){
            List<Node> visited = new ArrayList<>();
            Stack<Node> toVisit = new Stack<>();
            toVisit.add(u);
            transClosure.addNode(u.getId());
            while (!toVisit.isEmpty()) {
                Node current = toVisit.pop();
                if(!visited.contains(current)){
                    for (Edge v : getOutEdges(current)){ // here 'v' is an edge but the v from the algorithm is actually the @to of that edge  
                        if(!transClosure.existsEdge(u.getId(), v.to().getId())){
                            transClosure.addEdge(u.getId(), v.to().getId());
                            toVisit.remove(v.to());
                            toVisit.add(v.to());
                        }
                    }
                    visited.add(current);
                }
            }
        }

        return transClosure;
    }

    public static UndirectedGraph fromDotFile(String filename) {
        return (UndirectedGraph) Graph.fromDotFile(filename);
    }

    public static UndirectedGraph fromDotFile(String filename, String extension) {
        return null;
    }
}
