import java.util.*;

public class Node implements Comparable<Node>{

    /* Attributes */

    /**
     * 
     * id is an identifier
     * Two nodes in the same graph can't have the same id
     * Two nodes in different graphs can have the same id 
     */
    private final int id;

    /**
     * 
     * A node is not obligated to have a name, it can have it or not depend of the constructor 
     */
    private String name;

    /**
     * 
     * graphHolder is a reference to the graph where the node belongs
     */
    private final Graph graphHolder;

    /* Constructors */

    /**
     * Constructor for a node without a name
     * 
     * @param id            The id of the Node
     * @param graphHolder   The reference to the graph where the node will belongs
     * 
     * If the graph already contain a Node with this id the node isn't created. 
     * If the graph don't have the id, the programm create a node and add it to the graph
     */
    public Node(int id, Graph graphHolder){
        this.id = id;
        this.graphHolder = graphHolder;
    }
    
    /**
     * Constructor for a node with a name
     * 
     * @param id
     * @param name
     * @param graphHolder
     * 
     * If the graph already contain a Node with this id the node isn't created. 
     * If the graph don't have the id, the programm create a node and add it to the graph
     */
    public Node(int id, String name, Graph graphHolder){
        this.id = id;
        this.name = name;
        this.graphHolder = graphHolder;
    }

    /**
     * Constructor for a node without a name and the id specified
     * 
     * @param graphHolder
     * 
     * Create a node in the graph with the addition between the largest node id in the graph and 1 as the id.
     */
    public Node(Graph graphHolder){
        this.graphHolder = graphHolder;
        this.id = graphHolder.largestNodeId() + 1;
    }
    
    /* API */

    public int getId() {
        return id;
    }

    /**
     * The goal is getting a list without duplicates of the successors (or neighbours in the undirected case) of node this.
     * 
     * @return a list of all the successors without duplicate
     */
    public List<Node> getSuccessors(){
        return this.graphHolder.getSuccessors(this);
    }

    /**
     * The goal is getting a list with possible duplicates of the successors (or neighbours in the undirected case) of node this. 
     * 
     * @return a list of all the successors with possible duplicates
     */
    public List<Node> getSuccessorsMulti(){
        return this.graphHolder.getSuccessorsMulti(this);
    }

    /**
     * Test if a Node is adjacent to this node. 
     * 
     * @param u is a node 
     *
     * @return
     */
    public boolean adjacent(Node u){
        return this.graphHolder.adjacent(this, u);
    }

    public boolean adjacent(int id){
        return this.graphHolder.adjacent(this.id, id);
    }

    public int inDegree(){
        return this.graphHolder.inDegree(this);
    }
    
    public int outDegree(){
        return this.graphHolder.outDegree(this);
    }

    public int degree(){
        return this.graphHolder.degree(this);
    }

    public List<Edge> getOutEdges(){
        return this.graphHolder.getOutEdges(this);
    }   

    public List<Edge> getInEdges(){
        return this.graphHolder.getInEdges(this);
    }

    public List<Edge> getIncidentEdges(){
        return this.graphHolder.getIncidentEdges(this);
    }

    public List<Edge> getEdgesTo(Node u){
        return this.graphHolder.getEdges(this, u);
    }

    public Graph getGraph() {
        return graphHolder;
    }

    /* Overrides */

    /**
     * 
     */
    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(this.getClass() != obj.getClass()){
            return false;
        }
        Node other = (Node) obj;
        return this.id == other.id && this.graphHolder.equals(other.graphHolder);
    }

    /**
     * 
     */
    @Override
    public int hashCode(){
        int hash = 1;
        hash = hash * 17 * this.id;
        hash = hash * 17 * (this.graphHolder != null ? this.graphHolder.hashCode() : 0);
        return hash;
    }

    /**
     * 
     * @param o
     * @return
     */
    @Override
    public int compareTo(Node o) {
        return id - o.id;
    }
}