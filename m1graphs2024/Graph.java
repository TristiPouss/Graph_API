import java.io.*;
import java.util.*;

public class Graph {
    public static final boolean dev = true; 

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

        int x = 1;
        for (int i = 0; i < sa.length; i++) {
            addNode(x);
            if(sa[i] != 0) {
                if (dev) System.out.println("Adding Edge from " + (x) + " to " + sa[i]);
                addEdge(x, sa[i]);
            }
            else {
                x++;
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
        return (this.usesNode(n) && this == n.getGraph());
    }

    /**
     * @param id an integer
     * 
     * @return the node with the corresponding id or null if it doesnt exist in this graph
     */
    public Node getNode(int id){

        var allNodes = adjEdList.keySet();
        for (Node n : allNodes){
            if (n.getId() == id){
                return n;
            }
        }
        return null;
        /*List<Node> allNodes = this.getAllNodes();
        Node search = new Node(id, this);
        int isInside = allNodes.indexOf(search);
        if(isInside == -1){
            return null;
        }
        return allNodes.get(isInside);*/
    }

    
    /** 
     * @param n
     * @return boolean
     */
    public boolean addNode(Node n){
        if(this.usesNode(n)){
            return false;
        }
        List<Edge> emptyEdgeList = new ArrayList<>();
        adjEdList.put(n, emptyEdgeList);
        return true;
    }

    
    /** 
     * @param n
     * @return boolean
     */
    public boolean addNode(int n){
        if(this.usesNode(n)){
            return false;
        }
        if (dev) System.out.println("Adding Node " + n);
        adjEdList.put(new Node(n, this), new ArrayList<>());
        if (dev) System.out.println("Adjacency List : " + adjEdList);
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
        List<Edge> allInEdges = getInEdges(id);
        ListIterator<Edge> inIterator = allInEdges.listIterator();
        while(inIterator.hasNext()){
            removeEdge(inIterator.next());
        }
        Node n = new Node(id, this);
        adjEdList.remove(n);
        return true;
    }

    public List<Node> getAllNodes(){
        Set<Node> keys = adjEdList.keySet();
        List<Node> allNodes = new ArrayList<>(keys.size());
        allNodes.addAll(keys);
        Collections.sort(allNodes);
        return allNodes;
    }

    public int largestNodeId(){
        Set<Node> nodes = adjEdList.keySet();
        Iterator<Node> ite = nodes.iterator();
        int max = 0;
        while(ite.hasNext()){
            Node curr = ite.next();
            if(curr.getId() > max){
                max = curr.getId();
            }
        }
        return max;
    }

    public int smallestNodeId(){
        Set<Node> nodes = adjEdList.keySet();
        Iterator<Node> ite = nodes.iterator();
        int min = 0;
        while(ite.hasNext()){
            Node curr = ite.next();
            if(min == 0){
                min = curr.getId();
            }else{
                if(curr.getId() < min){
                    min = curr.getId();
                }
            }
        }
        return min;
    }

    public List<Node> getSuccessors(Node n){
        List<Node> result = new ArrayList<>();
        List<Edge> allEdges = getOutEdges(n);
        ListIterator<Edge> ite = allEdges.listIterator();
        while(ite.hasNext()){
            Edge curr = ite.next();
            if(result.indexOf(curr.to()) == -1){
                result.add(curr.to());
            }
        }
        return result;
    }

    public List<Node> getSuccessors(int id ){
        Node n = getNode(id);
        return getSuccessors(n);
    }

    public List<Node> getSuccessorsMulti(Node n){
        List<Node> result = new ArrayList<>();
        List<Edge> allEdges = getOutEdges(n);
        ListIterator<Edge> ite = allEdges.listIterator();
        while(ite.hasNext()){
            result.add(ite.next().to());
        }
        return result;
    }

    public List<Node> getSuccessorsMulti(int id){
        Node n = getNode(id);
        return getSuccessorsMulti(n);
    }

    public boolean adjacent(Node u, Node v){
        return existsEdge(u, v);
    }

    public boolean adjacent(int u, int v){
        return existsEdge(u, v);
    }

    public int inDegree(Node n){
        return getInEdges(n).size();
    }

    public int inDegree(int n){
        return getInEdges(n).size();
    }

    public int outDegree(Node n){
        return getOutEdges(n).size();
    }

    public int outDegree(int n){
        return getOutEdges(n).size();
    }

    public int degree(Node n){
        return getOutEdges(n).size() + getInEdges(n).size();
    }

    public int degree(int n){
        return getOutEdges(n).size() + getInEdges(n).size();
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
        int size = 0;
        for (var l : adjEdList.values()){
            for (Edge e : l){
                if (dev) System.out.println(e.from().getId());
                size ++;
            }
        }
        return size;
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
        addNode(from);
        addNode(to);

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
        addNode(from);
        addNode(to);

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
        addNode(from);
        addNode(to);
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
        addNode(from);
        addNode(to);

        adjEdList.get(getNode(from)).add(new Edge(from, to, weight, this));
    }

    /**
     * Add an edge in the graph
     * @param e Edge reference
     */
    public void addEdge(Edge e){
        if (existsEdge(e.from(), e.to())) return;
        addNode(e.from());
        addNode(e.to());

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
        Collections.sort(res);
        return res;
    }

    /**************************
     *                        *
     *   Representation and   *
     * Transformation methods *
     *                        *
     **************************/

    public int[] toSuccessorArray(){
        int[] result = new int[nbEdges() + nbNodes()];
        int compteur = 0;
        for (Node curr : getAllNodes()) {
            System.out.println("Node : " + curr.getId());
            List<Edge> successors = adjEdList.get(curr);
            Iterator<Edge> iteEdge = successors.iterator();
            while(iteEdge.hasNext()){
                result[compteur] = iteEdge.next().to().getId();
                compteur++;
            }
            result[compteur] = 0;
            compteur++;
        }

        if(dev){
            System.out.print("Print of the successor array : [");
            int ite = 0;
            for(; ite < result.length; ite++){
                System.out.print(result[ite] + " | ");
            }
            System.out.print("]");
        }

        return result;
    }

    public int[][] toAdjMatrix(){
        int[][] adjMatrix = new int[nbNodes()][nbNodes()];
        for (Edge e : getAllEdges()){
            adjMatrix[e.from().getId()-1][e.to().getId()-1]++;
        }
        if (dev) {
            for (int row = 0; row < nbNodes(); row++){
                System.out.println("");
                for (int col = 0; col < nbNodes(); col++){
                    System.out.print(adjMatrix[row][col] + " ");
                }   
            }
            System.out.println("");
        }
        return adjMatrix;
    }

    public Graph getReverse(){
        Graph reverse = new Graph();

        for (Node n : getAllNodes()) {
            reverse.addNode(n);
        }

        for (Edge e : getAllEdges()) {
            reverse.addEdge(e.getSymmetric());
        }

        return reverse;
    }

    public Graph getTransitiveClosure(){
        Graph transClosure = this.copy();

        for (Node origin : transClosure.getAllNodes()){
            List<Node> visited = new ArrayList<>();
            Queue<Node> toVisit = new PriorityQueue<>();
            toVisit.add(origin);
            while (!toVisit.isEmpty()) {
                Node current = toVisit.remove();
                if(!visited.contains(current)){
                    for (Edge e : transClosure.adjEdList.get(current)){
                        transClosure.addEdge(origin.getId(), e.to().getId());
                        toVisit.add(e.to());
                    }
                    visited.add(current);
                }
            }
        }

        return transClosure;
    }

    public boolean isMultiGraph(){
        List<Edge> allEdges = new ArrayList<>();
        for(Edge e : getAllEdges()){
            Edge newEdge = new Edge(e.from(), e.to(), this);
            if(allEdges.contains(newEdge)){
                return true;
            }
            allEdges.add(newEdge);
        }
        return false;
    }

    public boolean isSimpleGraph(){
        return !(isMultiGraph() || hasSelfLoops());
    }

    public boolean hasSelfLoops(){
        for(Edge e : getAllEdges()){
            if(e.from().equals(e.to())){
                return true;
            }
        }
        return false;
    }

    public Graph toSimpleGraph(){
        return this;
    }

    public Graph copy(){
        Graph copy = new Graph();

        for (Node n : getAllNodes()) {
            copy.addNode(new Node(n.getId(), copy));
        }

        for (Edge e : getAllEdges()) {
            if (!e.isWeighted()) copy.addEdge(new Edge(e.from(), e.from(), copy));
            else copy.addEdge(new Edge(e.from(), e.from(), e.getWeight(), copy));
            
        }

        return copy;
    }

    /**************************
     *                        *
     *     Graph Traversal    *
     *                        *
     **************************/

    public List<Node> getDFS(){
        return getDFS(getAllNodes().get(0));
    }

    public List<Node> getDFS(Node u){
        List<Node> visited = new ArrayList<>();
        Stack<Node> toVisit = new Stack<>();
        toVisit.add(u);
        while (!toVisit.isEmpty()) {
            Node current = toVisit.pop();
            visited.add(current);
            List<Edge> li = adjEdList.get(current);
            li.sort(Comparator.reverseOrder());
            for(Edge e : li){
                if(!visited.contains(e.to())){
                    // Put e.to() to the top of the toVisit stack
                    toVisit.remove(e.to());
                    toVisit.add(e.to());
                }
            }
        }
        return visited;
    }

    public List<Node> getDFS(int u){
        return getDFS(this.getNode(u));
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
        String dotString = "digraph G {";

        for (Edge e : getAllEdges()){
            dotString += "\n\t" + e.from().getId() + " -> " +e.to().getId();
        }

        dotString += "\n}";
        return dotString;
    }

    public void toDotFile(String fileName) throws IOException {
        toDotFile(fileName, ".gv");
    }

    public void toDotFile(String fileName, String extension) {
        fileName += extension;
        try {
            FileWriter dotFileWriter = new FileWriter(fileName);
            dotFileWriter.write(toDotString());
            dotFileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred. Could not create the file.");
            e.printStackTrace();
        }
    }
}