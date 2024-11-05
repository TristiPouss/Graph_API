import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * An extend of the class Graph to represent an undirected graph
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


    @Override
    public String toDotString() {
        String dotString = "graph {";

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
    public UndirectedGraph getReverse(){
        return copy();
    }

    @Override
    public UndirectedGraph copy(){
        UndirectedGraph copy = new UndirectedGraph();
        boolean add = false;

        for (Edge e : super.getAllEdges()) {
            System.out.println(e.from() + " -> " + e.to());
            if(e.from().getId() <= e.to().getId()){
                System.out.println("Passe");
                if(e.from() == e.to()){
                    if(!add){
                        System.out.println("Valide");
                        if (!e.isWeighted()) copy.addEdge(new Edge(e.from().getId(), e.to().getId(), copy));
                        else copy.addEdge(new Edge(e.from().getId(), e.to().getId(), e.getWeight(), copy));
                        add = true;
                    }else{
                        add = false;
                    }
                }else{
                    System.out.println("Valide");
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
    public boolean existsEdge(int u, int v) {
        return super.existsEdge(u, v) && super.existsEdge(v, u);
    }

    @Override
    public boolean existsEdge(Node u, Node v) {
        return super.existsEdge(u, v) && super.existsEdge(v, u);
    }

    @Override
    public boolean existsEdge(Edge e) {
        return super.existsEdge(e) && super.existsEdge(e.to(), e.from());
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
                    for (Edge v : getOutEdges(current.getId())){ // here 'v' is an edge but the v from the algorithm is actually the @to of that edge 
                        if(!transClosure.existsEdge(u.getId(), v.to().getId())){
                            transClosure.addEdge(u.getId(), v.to().getId());
                        }
                        toVisit.remove(v.to());
                        toVisit.add(v.to());
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
