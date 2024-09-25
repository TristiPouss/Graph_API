import java.util.*;

public class Graph {
    /* Attributes */

    /*
     * The Adjencent Edge List implemented as a Map
     */
    private Map<Node, List<Edge>> adjEdList;

    /* Constructors */

    /**
     * Constructor for empty graph
     */
    public Graph() {
        adjEdList = new HashMap<>();
    }
    
    /**
     * Constructor from Successor array
     * @param sa unknown number of int as successor array
     */
    public Graph(int ... sa) {
        adjEdList = new HashMap<>();

        addNode(0);
        for (int i = 0; i < sa.length; i++) {
            if(sa[i] == 0) addNode(i+1);
            else {
                addEdge(i, sa[i]);
            }
        }
    }

    /* API */

    /**************************
     *                        *
     * Nodes related methods  *
     *                        *
     **************************/

    /**
     * @return the number of nodes in the graph
     */
    public int nbNodes(){
        return adjEdList.size();
    }

    public boolean usesNode(Node n){
        return false;
    }

    public boolean usesNode(int n){
        return false;
    }

    public boolean holdsNode(Node n){
        return false;
    }

    /**
     * @param id an integer
     * @return the node with the corresponding id or null if it doesnt exist in this graph
     */
    public Node getNode(int id){
        return null;
    }

    public boolean addNode(Node n){
        return false;
    }

    public boolean addNode(int n){
        return false;
    }

    public boolean removeNode(Node n){
        return false;
    }

    public List<Node> getAllNodes(){
        return null;
    }

    public int largestNodeId(){
        return 0;
    }

    public int smallestNodeId(){
        return 0;
    }

    public List<Node> getSuccessors(Node n){
        return null;
    }

    public List<Node> getSuccessorsMulti(Node n){
        return null;
    }

    public boolean adjacent(Node u, Node v){
        return false;
    }

    public int inDegree(Node n){
        return 0;
    }

    public int outDegree(Node n){
        return 0;
    }

    public int degree(Node n){
        return 0;
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
    public int nbEdges() {
        return adjEdList.values().size();
    }

    /**
     * For knowing whether an edge exists in this graph between nodes u and v.
     * @param u any Node 
     * @param v any Node
     * @return  false if any node isn't in the graph or if no edges exists between the two,
     *          true else.
     */
    public boolean existsEdge(Node u, Node v){
        return usesNode(u) && usesNode(v) && adjEdList.containsKey(u) && adjEdList.get(u).contains(new Edge(this, u, v));
    }

    /**
     * For knowing whether an edge exists in this graph between nodes u and v.
     * @param u any Node id
     * @param v any Node id
     * @return  false if any node isn't in the graph or if no edges exists between the two,
     *          true else.
     */
    public boolean existsEdge(int u, int v){
        return usesNode(u) && usesNode(v) && adjEdList.get(getNode(u)).contains(new Edge(this, u, v));
    }

    /**
     * For knowing whether an edge exists in this graph between nodes u and v.
     * @param e Edge reference
     * @return  true if the edge is in the graph,
     *          false else.
     */
    public boolean existsEdge(Edge e){
        return usesNode(e.from()) && usesNode(e.to()) && adjEdList.get(e.from()).contains(e);
    }

    /**
     * Add an edge in the graph, if any Node does not exist, they have to
     * be created first.
     * @param from a Node
     * @param to a Node
     */
    public void addEdge(Node from, Node to){
        if (existsEdge(from, to)) return;
        if (!usesNode(from)) addNode(from);
        if (!usesNode(to)) addNode(to);

        adjEdList.get(from).add(new Edge(this, from, to));
    }

    /**
     * Add an edge in the graph, if any Node does not exist, they have to
     * be created first.
     * @param from a Node
     * @param to a Node
     * @param weight the weight of the edge
     */
    public void addEdge(Node from, Node to, int weight){
        if (existsEdge(from, to)) return;
        if (!usesNode(from)) addNode(from);
        if (!usesNode(to)) addNode(to);

        adjEdList.get(from).add(new Edge(this, from, to, weight));
    }

    /**
     * Add an edge in the graph, if any Node does not exist, they have to
     * be created first.
     * @param from a Node id
     * @param to a Node id
     */
    public void addEdge(int from, int to){
        if (existsEdge(from, to)) return;
        if (!usesNode(from)) addNode(from);
        if (!usesNode(to)) addNode(to);

        adjEdList.get(getNode(from)).add(new Edge(this, from, to));
    }

    /**
     * Add an edge in the graph, if any Node does not exist, they have to
     * be created first.
     * @param from a Node id
     * @param to a Node id
     * @param weight the weight of the edge
     */
    public void addEdge(int from, int to, int weight){
        if (existsEdge(from, to)) return;
        if (!usesNode(from)) addNode(from);
        if (!usesNode(to)) addNode(to);

        adjEdList.get(getNode(from)).add(new Edge(this, from, to, weight));
    }

    /**
     * Add an edge in the graph
     * @param e Edge reference
     */
    public void addEdge(Edge e){
        if (existsEdge(e.from(), e.to())) return;
        if (!usesNode(e.from())) addNode(e.from());
        if (!usesNode(e.to())) addNode(e.to());

        if(e.isWeighted()) adjEdList.get(e.from()).add(new Edge(this, e.from(), e.to(), e.getWeight()));
        else adjEdList.get(e.from()).add(new Edge(this, e.from(), e.to()));
    }

    /**
     * Remove the edge between @from and @to from this graph 
     * @param from a Node
     * @param to a Node
     * @return true if removal was a success
     *         false else.
     */
    public boolean removeEdge(Node from, Node to){
        return false;
    }

    /**
     * Remove the edge between @from and @to from this graph 
     * @param from a Node
     * @param to a Node
     * @param weight the weight of the edge
     * @return true if removal was a success
     *         false else.
     */
    public boolean removeEdge(Node from, Node to, int weight){
        return false;
    }

    /**
     * Remove the edge between @from and @to from this graph 
     * @param from a Node id
     * @param to a Node id
     * @return true if removal was a success
     *         false else.
     */
    public boolean removeEdge(int from, int to){
        return false;
    }

    /**
     * Remove the edge between @from and @to from this graph 
     * @param from a Node id
     * @param to a Node id
     * @param weight the weight of the edge
     * @return true if removal was a success
     *         false else.
     */
    public boolean removeEdge(int from, int to, int weight){
        return false;
    }

    /**
     * Remove the edge between @from and @to from this graph 
     * @param e the edge reference to remove
     * @return true if removal was a success
     *         false else.
     */
    public boolean removeEdge(Edge e){
        return false;
    }

    /**
     * For getting the list of edges leaving the Node @n
     * @param n a Node 
     * @return the list of edges leaving the node 
     */
    public List<Edge> getOutEdges(Node n){
        return null;
    }

    /**
     * For getting the list of edges leaving the Node @n
     * @param n a Node id
     * @return the list of edges leaving the node 
     */
    public List<Edge> getOutEdges(int n){
        return null;
    }

    /**
     * For getting the list of edges entering the Node @n
     * @param n a Node 
     * @return the list of edges entering the node 
     */
    public List<Edge> getInEdges(Node n){
        return null;
    }

    /**
     * For getting the list of edges entering the Node @n
     * @param n a Node id
     * @return the list of edges entering the node 
     */
    public List<Edge> getInEdges(int n){
        return null;
    }

    /**
     * for getting the list of all edges incident to node @n. 
     * This is the union of the out and in edges
     * @param n a Node 
     * @return the list of edges entering and leaving the node 
     */
    public List<Edge> getIncidentEdges(Node n){
        return null;
    }

    /**
     * for getting the list of all edges incident to node @n. 
     * This is the union of the out and in edges
     * @param n a Node id
     * @return the list of edges entering and leaving the node 
     */
    public List<Edge> getIncidentEdges(int n){
        return null;
    }

    /**
     * for getting the list of all edges going from node u to node v. 
     * N.B. Theoretically, this is the intersection of the out edges from u and the in edges to v; but computing this intersection
     * is not efficient so it is better to simply get among the out edges from u the ones that lead to v.
     * @param u a Node
     * @param v a Node
     * @return the list of all edges going from node u to node v. 
     */
    public List<Edge> getEdges(Node u, Node v){
        return null;
    }

    /**
     * for getting the list of all edges going from node u to node v. 
     * N.B. Theoretically, this is the intersection of the out edges from u and the in edges to v; but computing this intersection
     * is not efficient so it is better to simply get among the out edges from u the ones that lead to v.
     * @param u a Node id
     * @param v a Node id
     * @return the list of all edges going from node u to node v. 
     */
    public List<Edge> getEdges(int u, int v){
        return null;
    }

    /**
     * for getting the list of all the edges of the graph.
     * @return the list of all edges in the graph.
     */
    public List<Edge> getAllEdges(){
        List<Edge> res = new ArrayList<>();
        for (List<Edge> edges : adjEdList.values()) {
            for (Edge e : edges) {
                res.add(e);
            }
        }
        return res;
    }

    /**************************
     *                        *
     *   Representation and   *
     * Transformation methods *
     *                        *
     **************************/

    public int[] toSuccessorArray(){
        return new int[0];
    }

    public int[][] toAdjMatrix(){
        return new int[0][0];
    }

    public Graph getReverse(){
        return this;
    }

    public Graph getTransitiveClosure(){
        return this;
    }

    public boolean isMultiGraph(){
        return false;
    }

    public boolean hasSelfLoops(){
        return false;
    }

    public Graph toSimpleGraph(){
        return this;
    }

    public Graph copy(){
        return this;
    }

}