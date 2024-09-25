import static java.lang.System.in;
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

    /* Nodes related methods */

    /**
     * @return the number of nodes in the graph
     */
    public int nbNodes(){
        return adjEdList.size();
    }

    public boolean usesNode(Node n){
        return false;
    }

    public boolean holdsNode(Node n){
        return false;
    }

    /**
     * @param id an integer
     * @return the node with the corresponding id
     */
    public Node getNode(int id){
        return null;
    }

    public boolean addNode(Node n){
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

    /* Edges related methods */

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
        return false;
    }

    /**
     * For knowing whether an edge exists in this graph between nodes u and v.
     * @param u any Node id
     * @param v any Node id
     * @return  false if any node isn't in the graph or if no edges exists between the two,
     *          true else.
     */
    public boolean existsEdge(int u, int v){
        return false;
    }

    /**
     * For knowing whether an edge exists in this graph between nodes u and v.
     * @param e Edge reference
     * @return  true if the edge is in the graph,
     *          false else.
     */
    public boolean existsEdge(Edge e){
        return false;
    }

    /**
     * Add an edge in the graph, if any Node does not exist, they have to
     * be created first.
     * @param from a Node
     * @param to a Node
     */
    public void addEdge(Node from, Node to){

    }

    /**
     * Add an edge in the graph, if any Node does not exist, they have to
     * be created first.
     * @param from a Node
     * @param to a Node
     * @param weight the weight of the edge
     */
    public void addEdge(Node from, Node to, int weight){

    }

    /**
     * Add an edge in the graph, if any Node does not exist, they have to
     * be created first.
     * @param from a Node id
     * @param to a Node id
     */
    public void addEdge(int from, int to){

    }

    /**
     * Add an edge in the graph, if any Node does not exist, they have to
     * be created first.
     * @param from a Node id
     * @param to a Node id
     * @param weight the weight of the edge
     */
    public void addEdge(int from, int to, int weight){

    }

    /**
     * Add an edge in the graph, if any Node does not exist, they have to
     * be created first.
     * @param e Edge reference
     */
    public void addEdge(Edge e){

    }

    public boolean removeEdge(Node from, Node to){
        return false;
    }

    public List<Edge> getOutEdges(Node n){
        return null;
    }

    public List<Edge> getInEdges(Node n){
        return null;
    }

    public List<Edge> getIncidentEdges(Node n){
        return null;
    }

    public List<Edge> getEdges(Node u, Node v){
        return null;
    }

    public List<Edge> getAllEdges(){
        return null;
    }

    /* Representation and Transformation methods */

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