import java.util.*;

public class Graph {
    /* Attributes */

    private Map<Node, List<Edge>> adjEdList;

    /* Constructors */

    public Graph() {
        adjEdList = new HashMap<>();
    }

    public Graph(int ... sa) {
        adjEdList = new HashMap<>();
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

    public int nbEdges() {
        return adjEdList.values().size();
    }

    public boolean existsEdge(Node u, Node v){
        return false;
    }

    public void addEdge(Node from, Node to){

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