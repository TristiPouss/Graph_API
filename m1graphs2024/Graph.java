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
        return adjEdList.keySet().size();
    }

    /**
     * 
     * @param n a node 
     * 
     * @return true if the id is already used in this graph or false if not
     */
    public boolean usesNode(Node n){
        return this.getNode(n.getId()) != null;
    }

    /**
     * 
     * @param id a int
     * 
     * @return true if the id is used in the graph or false if not
     */
    public boolean usesNode(int id){
        return this.getNode(id) != null;
    }

    /**
     * 
     * @param n a node
     * 
     * @return true if the node is in the graph or false if not
     */
    public boolean holdsNode(Node n){
        return (this.usesNode(n.getId()) && this == n.getGraph());
    }

    /**
     * @param id an integer
     * 
     * @return the node with the corresponding id or null if it doesnt exist in this graph
     */
    public Node getNode(int id){
        List<Node> allNodes = this.getAllNodes();
        Node search = new Node(id, this);
        int isInside = allNodes.indexOf(search);
        if(isInside == -1){
            return null;
        }
        return allNodes.get(isInside);
    }

    public boolean addNode(Node n){
        if(this.usesNode(n.getId())){
            return false;
        }
        List<Edge> newEdges = new ArrayList<>();
        adjEdList.put(n, newEdges);
        return true;
    }

    public boolean addNode(int n){
        if(this.usesNode(n)){
            return false;
        }
        Node newNode = new Node(n, this);
        List<Edge> newEdges = new ArrayList<>();
        adjEdList.put(newNode, newEdges);
        return true;
    }

    public boolean removeNode(Node n){
        if(!this.holdsNode(n)){
            return false;
        }
        List<Edge> allInEdges = getInEdges(n);
        ListIterator<Edge> inIterator = allInEdges.listIterator();
        while(inIterator.hasNext()){
            removeEdge(inIterator.next());
        }
        adjEdList.remove(n);
        return true;
    }

    public boolean removeNode(int id){
        if(!this.usesNode(id)){
            return false;
        }
        return true;
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

    public List<Node> getSuccessors(int id ){
        return null;
    }

    public List<Node> getSuccessorsMulti(Node n){
        return null;
    }

    public List<Node> getSuccessorsMulti(int id){
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
        return holdsNode(u) && holdsNode(v) && adjEdList.containsKey(u) && adjEdList.get(u).contains(new Edge(u, v, this));
    }

    /**
     * For knowing whether an edge exists in this graph between nodes u and v.
     * @param u any Node id
     * @param v any Node id
     * @return  false if any node isn't in the graph or if no edges exists between the two,
     *          true else.
     */
    public boolean existsEdge(int u, int v){
        return usesNode(u) && usesNode(v) && adjEdList.get(getNode(u)).contains(new Edge(u, v, this));
    }

    /**
     * For knowing whether an edge exists in this graph between nodes u and v.
     * @param e Edge reference
     * @return  true if the edge is in the graph,
     *          false else.
     */
    public boolean existsEdge(Edge e){
        return holdsNode(e.from()) && holdsNode(e.to()) && adjEdList.get(e.from()).contains(e);
    }

    /**
     * Add an edge in the graph, if any Node does not exist, they have to
     * be created first.
     * @param from a Node
     * @param to a Node
     */
    public void addEdge(Node from, Node to){
        if (existsEdge(from, to)) return;
        if (!holdsNode(from)) addNode(from);
        if (!holdsNode(to)) addNode(to);

        adjEdList.get(from).add(new Edge(from, to, this));
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
        if (!holdsNode(from)) addNode(from);
        if (!holdsNode(to)) addNode(to);

        adjEdList.get(from).add(new Edge(from, to, weight, this));
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

        adjEdList.get(getNode(from)).add(new Edge(from, to, this));
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

        adjEdList.get(getNode(from)).add(new Edge(from, to, weight, this));
    }

    /**
     * Add an edge in the graph
     * @param e Edge reference
     */
    public void addEdge(Edge e){
        if (existsEdge(e.from(), e.to())) return;
        if (!holdsNode(e.from())) addNode(e.from());
        if (!holdsNode(e.to())) addNode(e.to());

        if(!e.isWeighted()) adjEdList.get(e.from()).add(new Edge(e.from(), e.to(), this));
        else adjEdList.get(e.from()).add(new Edge(e.from(), e.to(), e.getWeight(), this));
    }

    /**
     * Remove the edge between @from and @to from this graph 
     * @param from a Node
     * @param to a Node
     * @return true if removal was a success
     *         false else.
     */
    public boolean removeEdge(Node from, Node to){
        if (!existsEdge(from, to)) return true;

        adjEdList.get(from).remove(new Edge(from, to, this));

        return existsEdge(from, to);
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
        if (!existsEdge(from, to)) return true;

        adjEdList.get(from).remove(new Edge(from, to, weight, this));

        return existsEdge(from, to);
    }

    /**
     * Remove the edge between @from and @to from this graph 
     * @param from a Node id
     * @param to a Node id
     * @return true if removal was a success
     *         false else.
     */
    public boolean removeEdge(int from, int to){
        if (!existsEdge(from, to)) return true;

        adjEdList.get(getNode(from)).remove(new Edge(from, to, this));

        return existsEdge(from, to);
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
        if (!existsEdge(from, to)) return true;

        adjEdList.get(getNode(from)).remove(new Edge(from, to, weight, this));

        return existsEdge(from, to);
    }

    /**
     * Remove the edge @e from this graph 
     * @param e the edge reference to remove
     * @return true if removal was a success
     *         false else.
     */
    public boolean removeEdge(Edge e){
        if (!existsEdge(e.from(), e.to())) return true;

        if (!e.isWeighted()) adjEdList.get(e.from()).remove(new Edge(e.from(), e.to(), this));
        else adjEdList.get(e.from()).remove(new Edge(e.from(), e.to(), e.getWeight(), this));

        return existsEdge(e.from(), e.to());
    }

    /**
     * For getting the list of edges leaving the Node @n
     * @param n a Node 
     * @return the list of edges leaving the node 
     */
    public List<Edge> getOutEdges(Node n){
        if (adjEdList.get(n) == null) return new ArrayList<>();
        return adjEdList.get(n);
    }

    /**
     * For getting the list of edges leaving the Node @n
     * @param n a Node id
     * @return the list of edges leaving the node 
     */
    public List<Edge> getOutEdges(int n){
        if (getNode(n) == null) return new ArrayList<>();
        Node node = getNode(n);
        if (adjEdList.get(node) == null) return new ArrayList<>();
        return adjEdList.get(node);
    }

    /**
     * For getting the list of edges entering the Node @n
     * @param n a Node 
     * @return the list of edges entering the node 
     */
    public List<Edge> getInEdges(Node n){
        List<Edge> res = new ArrayList<>();
        for (List<Edge> edges : adjEdList.values()){
            for (Edge e : edges){
                if (e.to() == n) res.add(e);
            }
        }
        return res;
    }

    /**
     * For getting the list of edges entering the Node @n
     * @param n a Node id
     * @return the list of edges entering the node 
     */
    public List<Edge> getInEdges(int n){
        if (getNode(n) == null) return new ArrayList<>();
        Node node = getNode(n);

        List<Edge> res = new ArrayList<>();
        for (List<Edge> edges : adjEdList.values()){
            for (Edge e : edges){
                if (e.to() == node) res.add(e);
            }
        }
        return res;
    }

    /**
     * for getting the list of all edges incident to node @n. 
     * This is the union of the out and in edges
     * @param n a Node 
     * @return the list of edges entering and leaving the node 
     */
    public List<Edge> getIncidentEdges(Node n){
        List<Edge> res = new ArrayList<>();
        for (List<Edge> edges : adjEdList.values()){
            for (Edge e : edges){
                if (e.to() == n || e.from() == n) res.add(e);
            }
        }
        return res;
    }

    /**
     * for getting the list of all edges incident to node @n. 
     * This is the union of the out and in edges
     * @param n a Node id
     * @return the list of edges entering and leaving the node 
     */
    public List<Edge> getIncidentEdges(int n){
        if (getNode(n) == null) return new ArrayList<>();
        Node node = getNode(n);

        List<Edge> res = new ArrayList<>();
        for (List<Edge> edges : adjEdList.values()){
            for (Edge e : edges){
                if (e.to() == node || e.from() == node) res.add(e);
            }
        }
        return res;
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
        List<Edge> res = new ArrayList<>();
        for (List<Edge> edges : adjEdList.values()){
            for (Edge e : edges){
                if (e.from() == u && e.to() == v) res.add(e);
            }
        }
        return res;
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
        if (getNode(u) == null || getNode(v) == null) return new ArrayList<>();
        Node nodeU = getNode(u);
        Node nodeV = getNode(v);

        List<Edge> res = new ArrayList<>();
        for (List<Edge> edges : adjEdList.values()){
            for (Edge e : edges){
                if (e.from() == nodeU && e.to() == nodeV) res.add(e);
            }
        }
        return res;
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

    /**************************
     *                        *
     *     Graph Traversal    *
     *                        *
     **************************/

    public List<Node> getDFS(){
        return null;
    }

    public List<Node> getDFS(Node u){
        return null;
    }

    public List<Node> getDFS(int u){
        return null;
    }

    public List<Node> getBFS() {
        return null;
    }

    public List<Node> getBFS(Node u) {
        return null;
    }

    public List<Node> getBFS(int u) {
        return null;
    }

    public List<Node> getDFSWithVisitInfo(Map<Node, NodeVisitInfo> nodeVisit, Map<Edge, EdgeVisitType> edgeVisit) {
        return null;
    }

    public List<Node> getDFSWithVisitInfo(Node u, Map<Node, NodeVisitInfo> nodeVisit, Map<Edge, EdgeVisitType> edgeVisit) {
        return null;
    }

    /**************************
     *                        *
     *      Graph Import      *
     *       and Export       *
     *                        *
     **************************/

    public static Graph fromDotFile(String filename) {
        return null;
    }

    public static Graph fromDotFile(String filename, String extension) {
        return null;
    }

    public String toDotString() {
        return "";
    }

    public void toDotFile(String fileName) {

    }

    public void toDotFile(String fileName, String extension) {

    }
}