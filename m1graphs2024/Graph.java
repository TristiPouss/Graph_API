import java.io.*;
import java.util.*;

public class Graph {
    public static final boolean dev = false; 
    static int time = 0;

    /* Attributes */

    /*
     * The Adjencent Edge List implemented as a Map
     */
    private Map<Node, List<Edge>> adjEdList;
    private String name;

    /* Constructors */

    /**
     * Constructor for empty graph
     */
    public Graph() {
        adjEdList = new HashMap<>();
    }

    public Graph(String name) {
        this.name = name;
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
        addNode(from);
        addNode(to);

        adjEdList.get(getNode(from)).add(new Edge(from, to, weight, this));
    }

    /**
     * Add an edge in the graph
     * @param e Edge reference
     */
    public void addEdge(Edge e){
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
        if (!existsEdge(from, to)) return false;

        adjEdList.get(from).remove(new Edge(from, to, this));

        return true;
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
        if (!existsEdge(from, to)) return false;

        adjEdList.get(from).remove(new Edge(from, to, weight, this));

        return true;
    }

    /**
     * Remove the edge between @from and @to from this graph 
     * @param from a Node id
     * @param to a Node id
     * @return true if removal was a success
     *         false else.
     */
    public boolean removeEdge(int from, int to){
        if (!existsEdge(from, to)) return false;

        adjEdList.get(getNode(from)).remove(new Edge(from, to, this));

        return true;
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
        if (!existsEdge(from, to)) return false;

        adjEdList.get(getNode(from)).remove(new Edge(from, to, weight, this));

        return true;
    }

    /**
     * Remove the edge @e from this graph 
     * @param e the edge reference to remove
     * @return true if removal was a success
     *         false else.
     */
    public boolean removeEdge(Edge e){
        if (!existsEdge(e.from(), e.to())) return false;

        adjEdList.get(e.from()).remove(e);

        return true;
    }

    /**
     * For getting the list of edges leaving the Node @n
     * @param n a Node 
     * @return the list of edges leaving the node 
     */
    public List<Edge> getOutEdges(Node n){
        if (adjEdList.get(n) == null) return new ArrayList<>();
        List<Edge> result = adjEdList.get(n);
        Collections.sort(result);
        return result;
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
        List<Edge> result = adjEdList.get(node);
        Collections.sort(result);
        return result;
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
        Collections.sort(res);
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
        Collections.sort(res);
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
        Graph transClosure = new Graph();

        for (Node origin : getAllNodes()){
            List<Node> visited = new ArrayList<>();
            Stack<Node> toVisit = new Stack<>();
            toVisit.add(origin);
            transClosure.addNode(origin.getId());
            while (!toVisit.isEmpty()) {
                Node current = toVisit.pop();
                if(!visited.contains(current)){
                    for (Edge e : adjEdList.get(current)){
                        transClosure.addEdge(origin.getId(), e.to().getId());
                        toVisit.remove(e.to());
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
        Graph result = this.copy();
        List<Edge> visited = new ArrayList<>();
        boolean delete = false;
        for(Edge e : result.getAllEdges()){
            if(e.to() == e.from()){
                result.removeEdge(e);
            }else{
                for(Edge e1 : visited){
                    if(e.from() == e1.from() && e.to() == e1.to()){
                        result.removeEdge(e);
                        delete = true;
                    }
                }
                if(delete){
                    delete = false;
                }else{
                    visited.add(e);
                }
            }
        }
        return result;
    }

    public Graph copy(){
        Graph copy = new Graph();

        for (Node n : getAllNodes()) {
            copy.addNode(new Node(n.getId(), copy));
        }

        for (Edge e : getAllEdges()) {
            if (!e.isWeighted()) copy.addEdge(new Edge(copy.getNode(e.from().getId()), copy.getNode(e.to().getId()), copy));
            else copy.addEdge(new Edge(copy.getNode(e.from().getId()), copy.getNode(e.to().getId()), e.getWeight(), copy));            
        }

        return copy;
    }

    /**************************
     *                        *
     *     Graph Traversal    *
     *                        *
     **************************/

    public List<Node> getDFS(){
        if(getAllNodes().isEmpty()){
            return null;
        }
        return getDFS(getAllNodes().get(0));
    }

    public List<Node> getDFS(Node u){
        if(u == null){
            return null;
        }
        List<Node> visited = new ArrayList<>();
        Stack<Node> toVisit = new Stack<>();
        var nodes = getAllNodes();
        nodes.sort(Comparator.reverseOrder());
        toVisit.addAll(nodes);
        toVisit.remove(u);
        toVisit.add(u);
        while (!toVisit.isEmpty()) {
            Node current = toVisit.pop();
            visited.add(current);
            List<Edge> li = getEdges(current, u);
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
        return getBFS(getAllNodes().get(0));
    }

    public List<Node> getBFS(Node u) {
        return getBFS(u, new ArrayList<>());
    }

    public List<Node> getBFS(int u) {
        return getBFS(getNode(u));
    }

    public List<Node> getBFS(Node u, List<Node> visited) {
        visited.add(u);
        List<Node> next = new ArrayList<>();
        next.add(u);
        while(!next.isEmpty()){
            Node curr = next.get(0);
            next.remove(0);
            List<Edge> neighbor = adjEdList.get(curr);
            for (Edge e : neighbor) {
                if(!visited.contains(e.to())){
                    visited.add(e.to());
                    next.add(e.to());
                }
            }
        }

        if(visited.size() < getAllNodes().size()){
            for (Node n1 : getAllNodes()) {
                if(!visited.contains(n1)){
                    visited = getBFS(n1, visited);
                }
            }
        }
        return visited;
    }

    public List<Node> getDFSWithVisitInfo(Map<Node, NodeVisitInfo> nodeVisit, Map<Edge, EdgeVisitType> edgeVisit) {
        if(getAllNodes().isEmpty()){
            return null;
        }
        return getDFSWithVisitInfo(getAllNodes().get(0), nodeVisit, edgeVisit);
    }

    public List<Node> getDFSWithVisitInfo(Node u, Map<Node, NodeVisitInfo> nodeVisit, Map<Edge, EdgeVisitType> edgeVisit) {
        if(u == null){
            return null;
        }
        for(Node n : getAllNodes()){
            nodeVisit.get(n).colour = NodeColour.WHITE;
            nodeVisit.get(n).predecessor = null;
        }
        time = 0;
        List<Node> visited = new ArrayList<>();
        getDFSWithVisitInfo_Visit(u, visited, nodeVisit, edgeVisit);
        return visited;
    }

    public void getDFSWithVisitInfo_Visit(Node u, List<Node> visited, Map<Node, NodeVisitInfo> nodeVisit, Map<Edge, EdgeVisitType> edgeVisit) {
        time++;
        nodeVisit.get(u).discovery = time;
        nodeVisit.get(u).colour = NodeColour.GRAY;
        visited.add(u);

        List<Edge> li = adjEdList.get(u);
        li.sort(Comparator.reverseOrder());
        for(Edge e : li){
            switch(nodeVisit.get(e.to()).colour){
                case BLACK:
                    // Forward or Cross
                    // TODO differenciate forward and cross 
                    edgeVisit.put(e, EdgeVisitType.FORWARD);
                    //edgeVisit.put(e, EdgeVisitType.CROSS);
                    break;
                case GRAY:
                    // Backward
                    edgeVisit.put(e, EdgeVisitType.BACKWARD);
                    break;
                case WHITE:
                    // Tree
                    edgeVisit.put(e, EdgeVisitType.TREE);
                    nodeVisit.get(e.to()).predecessor = u;
                    getDFSWithVisitInfo_Visit(e.to(), visited, nodeVisit, edgeVisit);
                    break;
                default:
                    break;
                
            }
        }

        nodeVisit.get(u).colour = NodeColour.BLACK;
        time++;
        nodeVisit.get(u).finished = time;
    }

    /**************************
     *                        *
     *      Graph Import      *
     *       and Export       *
     *                        *
     **************************/

    public static Graph fromDotFile(String filename) {
        return fromDotFile(filename, ".gv");
    }

    public static Graph fromDotFile(String filename, String extension) {
        if(!(extension.equals(".gv") || extension.equals(".dot"))){
            return null;
        }
        Graph result = null;
        File newFile = new File("./m1graphs2024/dotGraphsTestPW2/" + filename + extension);
        try{
            Scanner parser = new Scanner(newFile);
            while(parser.hasNextLine()){
                String curr = parser.nextLine().trim();

                if(curr.charAt(0) == '#' || curr.isEmpty()){
                    continue;
                }

                String[] token = curr.split("\\s+");
                if(curr.contains("{")){
                    if(token.length == 3){
                        System.out.println("Passe");
                        if(Objects.equals(token[2], "{")){
                            if(token[0].equals("digraph")){
                                result = new Graph(token[1]);
                            }else{
                                return null;
                            }
                        }
                    }else{
                        if(token[0].equals("digraph")){
                            result = new Graph();
                        }else{
                            return null;
                        }
                    }
                }

                if(token[token.length - 1].equals("}")){
                    return result;
                }
                
                if(result != null){
                    if(token.length >= 3){
                        if(token[1].equals("->")){
                            int node1 = Integer.parseInt(token[0]);
                            int node2 = Integer.parseInt(token[2]);
                            result.addNode(node1);
                            result.addNode(node2);

                            if(token.length > 3){
                                result.addEdge(node1, node2, Integer.parseInt(token[token.length - 1].split("=")[1].replace("]", "")));
                            }else{
                                result.addEdge(node1, node2);
                            }
                        }
                    }else{
                        if(token.length == 1 && token[0].matches("[0-9]+")){
                            result.addNode(Integer.parseInt(token[0]));
                        }
                    }
                }
            }
        }catch(FileNotFoundException f){
            throw new RuntimeException(f);
        }
        return result;
    }

    public String toDotString() {
        String dotString = "digraph G {";

        for (Node n : getAllNodes()){
            if(adjEdList.get(n).isEmpty()){
                dotString += "\n\t" + n;
            }else{
                for (Edge e : getOutEdges(n)){
                    dotString += "\n\t" + e.from() + " -> " + e.to();
                    if(e.isWeighted()) dotString += " [label=" + e.getWeight() + ", len=" + e.getWeight() + "]";
                }
            }
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